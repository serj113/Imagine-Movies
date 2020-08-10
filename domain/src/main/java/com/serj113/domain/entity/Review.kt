package com.serj113.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Review (
    var author: String,
    var content: String,
    var id: String,
    var url: String
) : Parcelable