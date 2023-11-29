package com.example.payments_test_task_project.login.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenResponse(
    @SerializedName("response")
    val response: LoginToken,
) : Parcelable