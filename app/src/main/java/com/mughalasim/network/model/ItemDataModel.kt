package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemDataModel (
    var metaData: MetaDataModel? = null,
    var mediaData: MediaDataModel? = null
) : Parcelable