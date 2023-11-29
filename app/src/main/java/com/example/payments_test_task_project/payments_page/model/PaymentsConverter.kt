package com.example.payments_test_task_project.payments_page.model

import com.example.payments_test_task_project.payments_page.api.PaymentResponse
import com.example.payments_test_task_project.payments_page.api.PaymentsResponse
import com.example.payments_test_task_project.utils.extensions.convertDate

object PaymentsConverter {
    fun convert(response: PaymentsResponse): Payments =
        Payments(convertList(response.payments))

    private fun convertList(response: List<PaymentResponse>): List<Payment> =
        response.map { result ->
            Payment(
                amount = result.amount ?: "The payment amount is unknown",
                created = result.created?.let { convertDate(it) } ?: "Unknown",
                id = result.id,
                title = result.title,
            )
        }
}