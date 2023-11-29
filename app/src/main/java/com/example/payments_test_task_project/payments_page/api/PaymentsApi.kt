package com.example.payments_test_task_project.payments_page.api

import retrofit2.http.GET
import retrofit2.http.Header

interface PaymentsApi {
    @GET("payments")
    suspend fun getMyPayments(
        @Header("token") token: String,
        @Header("app-key") appKey: String,
        @Header("v") v: String
    ): PaymentsResponse
}