package com.example.payments_test_task_project.login.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.payments_test_task_project.R
import com.example.payments_test_task_project.common.mvvm.BaseFragment
import com.example.payments_test_task_project.databinding.FragmentLoginPageBinding
import com.example.payments_test_task_project.login.model.LoginRequest
import com.example.payments_test_task_project.login.ui.viewmodel.LoginViewModel
import com.example.payments_test_task_project.payments_page.ui.fragment.PaymentsPageFragment
import com.example.payments_test_task_project.utils.Constants
import com.example.payments_test_task_project.utils.extensions.replace
import com.example.payments_test_task_project.utils.viewbinding.viewBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginPageFragment : BaseFragment(R.layout.fragment_login_page) {
    private val binding: FragmentLoginPageBinding by viewBinding()
    private val viewModel: LoginViewModel by viewModels()


    override fun bind() {}

    @SuppressLint("SetTextI18n")
    override fun initViews(view: View) {
        with(binding) {
            btnLogin.setOnClickListener {
                loginUser()
            }
        }
    }

    private fun loginUser() {
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        val username = binding.usernameInputText.text.toString()
        val password = binding.passwordInputText.text.toString()

        val loginUserRequest = LoginRequest(username, password)

        viewModel.login(loginUserRequest, appKey = Constants.APP_KEY, v = Constants.V)

        viewModel.responseLiveData.observe(viewLifecycleOwner) { response ->
            if (response.body()?.response?.token == null) {
                view?.let {
                    Snackbar.make(
                        it,
                        "Неправильный юзернейм или пароль, проверьте их и повторите попытку.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

            } else {
                val sharedPreference = activity?.getSharedPreferences(
                    "TOKEN_PREF",
                    Context.MODE_PRIVATE
                )

                val editor = sharedPreference?.edit()
                val token =
                    response.body()!!.response.token

                editor?.putString("TOKEN", token)
                editor?.apply()
                view?.let {
                    Snackbar.make(
                        it,
                        "Аутентификация успешна!",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                replace(PaymentsPageFragment())
            }
        }
    }
}