package com.eslam.flickers_app.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eslam.flickers_app.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}