package com.eslam.flickers_app.util

import android.app.Activity
import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import com.example.awesomedialog.AwesomeDialog
import com.example.awesomedialog.body
import com.example.awesomedialog.onNegative
import com.example.awesomedialog.onPositive
import com.example.awesomedialog.title
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.eslam.flickers_app.R
import java.text.SimpleDateFormat
import java.util.Locale

object AlertHelper {

    fun Activity.createDialog(
        title: String,
        msg: String,
        positiveTitle: String,
        positive: (dialog: androidx.appcompat.app.AlertDialog) -> Unit,
        negativeTitle: String? = null,
        negative: (dialog: androidx.appcompat.app.AlertDialog) -> Unit? = { }
    ) {
        val awesomeDialog = AwesomeDialog.build(this)
        awesomeDialog.title(title)
            .body(msg)
            .onPositive(positiveTitle) {
                positive(awesomeDialog)
            }
            .setCancelable(false)
        if (!negativeTitle.isNullOrEmpty()) {
            awesomeDialog.onNegative(negativeTitle) {
                negative(awesomeDialog)
            }
        }
    }
}