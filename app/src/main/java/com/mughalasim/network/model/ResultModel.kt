package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultModel (
    var sections: List<SectionsModel>? = null,
) : Parcelable