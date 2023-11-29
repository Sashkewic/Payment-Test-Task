package com.example.payments_test_task_project.login.repository

import com.example.payments_test_task_project.login.api.LoginApi
import com.example.payments_test_task_project.login.di.LoginModule.Companion.IoDispatcher
import com.example.payments_test_task_project.login.model.LoginRequest
import com.example.payments_test_task_project.login.model.LoginToken
import com.example.payments_test_task_project.login.model.TokenResponse
import com.example.payments_test_task_project.utils.RetrofitInstance
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class LoginRemoteRepositoryImpl @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val api: LoginApi
) : LoginRemoteRepository {
    override suspend fun authUser(
        data: LoginRequest,
        appKey: String,
        v: String
    ): Response<TokenResponse> =
        withContext(ioDispatcher) {
            RetrofitInstance.loginApi.login(data, appKey, v)
        }
}