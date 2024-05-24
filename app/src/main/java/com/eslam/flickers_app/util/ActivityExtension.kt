package com.eslam.flickers_app.util

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : AppCompatActivity> Activity.openActivity(finish: Boolean = true) {
    val intent = Intent(
        this, T::class.java
    ).also {
        it.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    }

    startActivity(intent)
    if (finish) {
        finish()
    }
}
