package com.example.payments_test_task_project.payments_page.repository

import com.example.payments_test_task_project.payments_page.api.PaymentsApi
import com.example.payments_test_task_project.payments_page.di.PaymentModule.Companion.IoDispatcher
import com.example.payments_test_task_project.payments_page.model.Payments
import com.example.payments_test_task_project.payments_page.model.PaymentsConverter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PaymentsRemoteRepositoryImpl @Inject constructor(
    val api: PaymentsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
): PaymentsRemoteRepository {
    override suspend fun getMyPayments(token: String, appKey: String, v: String): Payments =
        withContext(ioDispatcher){
            PaymentsConverter.convert(api.getMyPayments(token, appKey, v))
        }
}