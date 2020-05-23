package com.serj113.imaginemovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.serj113.imaginemovies.R

class MainActivity : AppCompatActivity() {

    override fun onSupportNavigateUp() =
        findNavController(R.id.mainNavigationFragment).navigateUp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
