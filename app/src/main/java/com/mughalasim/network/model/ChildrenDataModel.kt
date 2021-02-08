package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChildrenDataModel @JvmOverloads constructor (
        var data: DataTwoModel = DataTwoModel(),
        val kind: String = "",

        var is_loading: Boolean? = false,
        var can_load_more: Boolean? = false
) : Parcelable