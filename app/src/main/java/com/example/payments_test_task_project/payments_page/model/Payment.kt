package com.example.payments_test_task_project.payments_page.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Payment(
//    val amount: String?,
//    val created: Int?,
    val id: Int,
    val title: String
) : Parcelable