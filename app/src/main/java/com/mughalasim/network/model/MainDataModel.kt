package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainDataModel constructor (
        val data: DataOneModel,
        val kind: String
) : Parcelable