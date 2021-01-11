package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PeopleModel @JvmOverloads constructor (
        var name: String? = "",
        var height: String? = "",
        var mass: String? = "",
        var created: String? = "",
        var is_loading: Boolean? = false,
        var can_load_more: Boolean? = false
) : Parcelable