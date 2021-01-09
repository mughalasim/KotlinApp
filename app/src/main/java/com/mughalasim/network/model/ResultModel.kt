package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ResultModel (
        var results: List<PeopleModel>? = null,
        var next: String? = null
) : Parcelable