package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SectionsModel (
    var name: String,
    var itemData: List<ItemDataModel>? = null
) : Parcelable