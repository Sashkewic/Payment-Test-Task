package com.example.payments_test_task_project.login.api

import com.example.payments_test_task_project.login.model.LoginRequest
import com.example.payments_test_task_project.login.model.LoginToken
import com.example.payments_test_task_project.login.model.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginApi {
    @POST("login")
    suspend fun login(
        @Body request: LoginRequest,
        @Header("app-key") appKey: String,
        @Header("v") v: String
    ): Response<TokenResponse>
}