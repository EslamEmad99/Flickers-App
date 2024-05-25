package com.eslam.flickers_app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eslam.flickers_app.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * The MainActivity serves as the container for the application's primary navigation components.
 * It hosts a NavHostFragment which is responsible for managing navigation between different fragments,
 * such as MoviesFragment and MovieDetailsFragment.
 * By using a NavHostFragment, we adhere to a single-activity architecture,
 * promoting a streamlined and maintainable navigation structure within the app.
 * **/
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}