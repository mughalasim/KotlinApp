package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PeopleModel(
        var name: String?,
        var height: String?,
        var mass: String?,
        var created: String?,
        var load_more: Boolean? = false
) : Parcelable