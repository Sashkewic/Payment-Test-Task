package com.example.payments_test_task_project.utils.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Lifecycle
import com.example.payments_test_task_project.R

fun Fragment.popScreen() {
    requireActivity().hideKeyboard()

    val fragmentManager = activity?.supportFragmentManager ?: childFragmentManager
    whenStateAtLeast(Lifecycle.State.STARTED) {
        fragmentManager.popBackStack()
    }
}

fun Fragment.replace(fragment: Fragment) {
    val fragmentManager = requireActivity().supportFragmentManager
    val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
    fragmentTransaction.addToBackStack(null)
        .replace(R.id.container, fragment)
        .commit()
}