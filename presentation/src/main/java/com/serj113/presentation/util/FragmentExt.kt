package com.serj113.presentation.util

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

fun Fragment.navigateTo(direction: NavDirections) {
    NavHostFragment.findNavController(this).navigate(direction)
}