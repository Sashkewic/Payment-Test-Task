package com.example.payments_test_task_project.payments_page.di

import com.example.payments_test_task_project.login.api.LoginApi
import com.example.payments_test_task_project.login.repository.LoginRemoteRepository
import com.example.payments_test_task_project.login.repository.LoginRemoteRepositoryImpl
import com.example.payments_test_task_project.payments_page.api.PaymentsApi
import com.example.payments_test_task_project.payments_page.repository.PaymentsRemoteRepository
import com.example.payments_test_task_project.payments_page.repository.PaymentsRemoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PaymentModule {
    @Binds
    @Singleton
    abstract fun bindRemoteRepository(repository: PaymentsRemoteRepositoryImpl): PaymentsRemoteRepository

    companion object {
        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): PaymentsApi =
            retrofit.create(PaymentsApi::class.java)

        @IoDispatcher
        @Provides
        fun provideContextIo(): CoroutineDispatcher = Dispatchers.IO

        @DefaultDispatcher
        @Provides
        fun provideContextDefault(): CoroutineDispatcher = Dispatchers.Default

        @Retention(AnnotationRetention.RUNTIME)
        @Qualifier
        annotation class IoDispatcher

        @Retention(AnnotationRetention.RUNTIME)
        @Qualifier
        annotation class DefaultDispatcher
    }
}