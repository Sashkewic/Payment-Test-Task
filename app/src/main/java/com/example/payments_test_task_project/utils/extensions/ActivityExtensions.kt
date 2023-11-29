package com.example.payments_test_task_project.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment


fun Activity.hideKeyboard() {
    currentFocus?.hideKeyboard()
}

fun Fragment.hideKeyboardDrop() {
    requireActivity().hideKeyboard()
}

fun View.hideKeyboard() {
    post {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }
}
