package com.example.payments_test_task_project.login.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String
) : Parcelable