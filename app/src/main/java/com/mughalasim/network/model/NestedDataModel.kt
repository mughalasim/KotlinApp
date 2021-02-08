package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NestedDataModel constructor (
        val data: DataOneModel,
        val kind: String
) : Parcelable