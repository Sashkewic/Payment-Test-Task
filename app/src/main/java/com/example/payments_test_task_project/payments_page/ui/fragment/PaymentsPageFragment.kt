package com.example.payments_test_task_project.payments_page.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.payments_test_task_project.R
import com.example.payments_test_task_project.common.mvvm.BaseFragment
import com.example.payments_test_task_project.databinding.FragmentPaymentsPageBinding
import com.example.payments_test_task_project.login.ui.fragment.LoginPageFragment
import com.example.payments_test_task_project.payments_page.ui.adapter.PaymentsAdapter
import com.example.payments_test_task_project.payments_page.ui.viewmodel.PaymentsViewModel
import com.example.payments_test_task_project.utils.Constants
import com.example.payments_test_task_project.utils.extensions.checkForInternet
import com.example.payments_test_task_project.utils.extensions.replace
import com.example.payments_test_task_project.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentsPageFragment : BaseFragment(R.layout.fragment_payments_page) {
    private val binding: FragmentPaymentsPageBinding by viewBinding()
    private val viewModel: PaymentsViewModel by viewModels()

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var adapter: PaymentsAdapter


    override fun bind() {
        getPayments()
    }

    override fun initViews(view: View) {
        with(binding) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = PaymentsAdapter()

            if (checkForInternet(requireContext())) {
                getPayments()
            } else {
                paymentsRecyclerView.isVisible = false
                networkFailWrapper.isVisible = true
            }

            repeatRequestBtn.setOnClickListener {
                repeatRequest()
            }

            logOutBtn.setOnClickListener {
                val sharedPreference = activity?.getSharedPreferences("TOKEN_PREF", Context.MODE_PRIVATE)
                val editor = sharedPreference?.edit()
                val token = ""
                editor?.putString("TOKEN", token)
                editor?.apply()
                replace(LoginPageFragment())
            }


            paymentsRecyclerView.adapter = adapter
            paymentsRecyclerView.layoutManager = layoutManager
            paymentsRecyclerView.setHasFixedSize(true)
            adapter.onAttachedToRecyclerView(paymentsRecyclerView)

        }
    }

    @SuppressLint("SetTextI18n")
    private fun getPayments() {
        val sharedPreference = activity?.getSharedPreferences("TOKEN_PREF", Context.MODE_PRIVATE)
        val token = sharedPreference?.getString("TOKEN", "")

        with(viewModel) {
            if (token != null) {
                loadMyPayments(token = token, appKey = Constants.APP_KEY, v = Constants.V)
            }
        }

        observe(viewModel.myPaymentsData) {
            adapter.setMyPayments(viewModel.myPaymentsData.value.payments)
        }
    }

    private fun repeatRequest() {
        with(binding) {
            if (checkForInternet(requireContext())) {
                getPayments()
                paymentsRecyclerView.isVisible = true
                networkFailWrapper.isVisible = false
            } else {
                paymentsRecyclerView.isVisible = false
                networkFailWrapper.isVisible = true
            }
        }
    }
}