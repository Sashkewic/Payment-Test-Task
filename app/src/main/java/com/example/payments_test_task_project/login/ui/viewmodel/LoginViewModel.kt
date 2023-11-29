package com.example.payments_test_task_project.login.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.payments_test_task_project.common.mvvm.BaseViewModel
import com.example.payments_test_task_project.login.model.LoginRequest
import com.example.payments_test_task_project.login.model.LoginToken
import com.example.payments_test_task_project.login.model.TokenResponse
import com.example.payments_test_task_project.login.repository.LoginRemoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.launch
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repositoryImpl: LoginRemoteRepositoryImpl,
) : BaseViewModel() {
    val responseLiveData = MutableLiveData<Response<TokenResponse>>()
    val isLoading = MutableLiveData<Boolean>()

    fun login(data: LoginRequest, appKey: String, v: String) {
        viewModelScope.launch {
            try {
                isLoading.value = true
                val response = repositoryImpl.authUser(data, appKey, v)
                responseLiveData.value = response
                Timber.i("Response ----> $responseLiveData")
            } catch (t: Throwable) {
                Timber.e(t.message)
            } catch (e: CancellationException) {
                Timber.e(e.message)
            } finally {
                isLoading.value = false
            }
        }
    }
}
