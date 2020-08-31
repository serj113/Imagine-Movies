package com.serj113.presentation.util

import java.text.DecimalFormat

object NumberUtils {
    fun formatNumber(number: Int): String? {
        val decimalFormat = DecimalFormat("#,###")
        return decimalFormat.format(number.toLong())
    }

    fun formatCurrency(number: Long): String? {
        val decimalFormat =
            DecimalFormat("$###,###")
        return decimalFormat.format(number)
    }
}