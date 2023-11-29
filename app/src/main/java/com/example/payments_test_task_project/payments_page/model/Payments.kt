package com.example.payments_test_task_project.payments_page.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payments(
    val payments: List<Payment>
) : Parcelable