package com.example.payments_test_task_project.utils.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SwitchCompat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.WeekFields
import java.util.Locale

@SuppressLint("SimpleDateFormat")
fun convertLongToDateUi(date: Long?): String {
    val formatter = SimpleDateFormat("dd MMM")
    return formatter.format(date)
}

@SuppressLint("SimpleDateFormat")
fun convertLongToDateResponse(date: Long): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    return formatter.format(date)
}

@SuppressLint("SimpleDateFormat")
fun getFirstDayOfCurrentWeek(): String {
    val localDate = LocalDate.now()
    val monday = localDate.with(WeekFields.of(Locale.UK).dayOfWeek(), 1L)
    val dateFormatUi = DateTimeFormatter.ofPattern("dd MMM")
    return dateFormatUi.format(monday)
}

@SuppressLint("SimpleDateFormat")
fun getLastDayOfCurrentWeek(): String {
    val localDate = LocalDate.now()
    val sunday = localDate.with(WeekFields.of(Locale.UK).dayOfWeek(), 7L)
    val dateFormatUi = DateTimeFormatter.ofPattern("dd MMM")
    return dateFormatUi.format(sunday)
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

@SuppressLint("SimpleDateFormat")
fun convertEuropeDateToCisDate(date: String) : String? {
    val parser = SimpleDateFormat("yyyy-MM-dd")
    val formatter = SimpleDateFormat("dd.MM.yyyy")
    return parser.parse(date)?.let { formatter.format(it) }
}

fun changeTheme(switchMode : SwitchCompat){
    if (switchMode.isChecked){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    }

    else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
