package com.glagouy.dmii

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Operation : Parcelable {
    SUM, PRODUCT, MINUS, DIVISE, PERCENT
}