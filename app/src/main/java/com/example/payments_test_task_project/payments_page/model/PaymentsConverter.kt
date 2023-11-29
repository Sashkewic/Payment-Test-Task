package com.example.payments_test_task_project.payments_page.model

import com.example.payments_test_task_project.payments_page.api.PaymentResponse
import com.example.payments_test_task_project.payments_page.api.PaymentsResponse

object PaymentsConverter {
    fun convert(response: PaymentsResponse): Payments =
        Payments(convertList(response.payments))

    private fun convertList(response: List<PaymentResponse>): List<Payment> =
        response.map { result ->
            Payment(
//                amount = result.amount,
//                created = result.created,
                id = result.id,
                title = result.title,
            )
        }
}