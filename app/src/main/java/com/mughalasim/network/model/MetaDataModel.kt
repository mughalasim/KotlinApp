package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MetaDataModel (
    var title: String,
    var VideoDuration: Int,
    var body: String
) : Parcelable