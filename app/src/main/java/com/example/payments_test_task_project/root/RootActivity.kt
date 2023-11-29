package com.example.payments_test_task_project.root

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.payments_test_task_project.R
import com.example.payments_test_task_project.databinding.ActivityRootBinding
import com.example.payments_test_task_project.login.ui.fragment.LoginPageFragment
import com.example.payments_test_task_project.payments_page.ui.fragment.PaymentsPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Скрываем дефолтный ActionBar
        supportActionBar?.hide()
        binding = ActivityRootBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Инициализируем sharedPreferences
        val sharedPreference = getSharedPreferences("TOKEN_PREF", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("TOKEN", "")

        // Если все поля не пусты, то вас сразу перенаправляют на страницу с платежами
        if (token != "") {
            replace(PaymentsPageFragment())
        }
        // Если хоть одно поле пустое, направляют на страницу авторизации (нужно заново логиниться)

        else {
            replace(LoginPageFragment())
        }

    }

    // Метод смены фрагмента через Activity
    private fun replace(fragment: Fragment, tag: String = fragment::class.java.name) {
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}
