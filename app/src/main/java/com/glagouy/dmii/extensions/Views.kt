package com.glagouy.dmii.extensions

import android.widget.EditText
import com.glagouy.dmii.R

fun EditText.toDouble() : Double? {
    return text.toString().toDoubleOrNull() ?: run {
        error = context.getString(R.string.number_error)
        null
    }
}