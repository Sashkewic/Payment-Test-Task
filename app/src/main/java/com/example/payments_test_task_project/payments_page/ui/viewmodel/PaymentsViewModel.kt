package com.example.payments_test_task_project.payments_page.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.payments_test_task_project.common.mvvm.BaseViewModel
import com.example.payments_test_task_project.payments_page.model.Payment
import com.example.payments_test_task_project.payments_page.model.Payments
import com.example.payments_test_task_project.payments_page.repository.PaymentsRemoteRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class PaymentsViewModel @Inject constructor(
    val repository: PaymentsRemoteRepositoryImpl
) : BaseViewModel() {
    private val _myPaymentsData = MutableStateFlow(Payments(emptyList()))
    val myPaymentsData = _myPaymentsData.asStateFlow()
    val isLoading = MutableLiveData<Boolean>()

    fun loadMyPayments(token: String, appKey: String, v: String) =
        launch {
            try {
                isLoading.value = true
                _myPaymentsData.tryEmit(repository.getMyPayments(token, appKey, v))
            } catch (e: CancellationException) {
                Timber.e(e.message)
            } catch (t: Throwable) {
                Timber.e(t.message)
            } finally {
                isLoading.value = false
            }
        }

}
