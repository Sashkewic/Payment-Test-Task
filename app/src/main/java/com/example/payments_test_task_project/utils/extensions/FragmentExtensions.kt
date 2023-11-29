package com.example.payments_test_task_project.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import timber.log.Timber
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Date
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun convertDate(date: String?): String {
    val newDate = date + "000"
    val longDate = newDate.toLong()
    Timber.i("LONG DATE ------> $longDate")
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:MM:SS")
    return formatter.format(longDate)
}

@SuppressLint("ServiceCast")
fun checkForInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false

    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

    return when {
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
        activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
        else -> false
    }
}