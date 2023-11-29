package com.example.payments_test_task_project.payments_page.repository

import com.example.payments_test_task_project.payments_page.model.Payments

interface PaymentsRemoteRepository {
    suspend fun getMyPayments(token: String, appKey: String, v: String): Payments
}