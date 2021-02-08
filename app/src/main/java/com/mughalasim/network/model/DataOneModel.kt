package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataOneModel @JvmOverloads constructor (
        val children: List<ChildrenDataModel> = arrayListOf(),
        var after: String? = null,
        val modhash: String = "",
        val dist: Int = 0
) : Parcelable