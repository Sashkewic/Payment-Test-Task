package com.example.payments_test_task_project.payments_page.api


import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("amount")
    val amount: String?,
    @SerializedName("created")
    val created: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)