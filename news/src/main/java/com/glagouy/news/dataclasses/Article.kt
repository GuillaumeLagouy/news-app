package com.glagouy.news.dataclasses

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article (
    val title:String,
    val description:String,
    val urlToImage:String,
    val url:String,
    var isFavorite:Boolean = false
) : Parcelable