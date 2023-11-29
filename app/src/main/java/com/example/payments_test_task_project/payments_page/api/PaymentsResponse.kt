package com.example.payments_test_task_project.payments_page.api


import com.google.gson.annotations.SerializedName

data class PaymentsResponse(
    @SerializedName("response")
    val payments: List<PaymentResponse>
)