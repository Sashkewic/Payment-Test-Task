package com.example.payments_test_task_project.utils.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <T, G> Flow<T>.collectFLow(
    dispatcher: CoroutineDispatcher,
    scope: CoroutineScope,
    func: suspend (T) -> G
): G {
    return suspendCancellableCoroutine { continuation ->
        val job = scope.launch {
            this@collectFLow.collect { flowData ->
                withContext(dispatcher) {
                    val data = func(flowData)
                    if (continuation.isActive) {
                        continuation.resume(data) {
                            this.cancel()
                        }
                    }
                }
                return@collect
            }
        }
        continuation.invokeOnCancellation { job.cancel() }
    }
}