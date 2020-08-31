package com.serj113.presentation.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

object DateUtils {
    fun formatDate(releaseDate: String?): String? {
        val dateFormat = SimpleDateFormat(
            "yyyy-MM-dd", Locale.getDefault()
        )
        var date: Date? = null
        try {
            date = dateFormat.parse(releaseDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val outputDateFormat = SimpleDateFormat(
            "MMM dd, yyyy", Locale.getDefault()
        )
        return outputDateFormat.format(date)
    }
}