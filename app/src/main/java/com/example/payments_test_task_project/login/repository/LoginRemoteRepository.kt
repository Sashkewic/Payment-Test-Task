package com.example.payments_test_task_project.login.repository

import com.example.payments_test_task_project.login.model.LoginRequest
import com.example.payments_test_task_project.login.model.LoginToken
import com.example.payments_test_task_project.login.model.TokenResponse
import retrofit2.Response

interface LoginRemoteRepository {
    suspend fun authUser(data: LoginRequest, appKey: String, v: String) : Response<TokenResponse>
}