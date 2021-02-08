package com.mughalasim.network.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DataTwoModel @JvmOverloads constructor (
        val author_fullname: String = "",
        val title: String = "",
        val link_flair_text_color: String = "",
        val score: Int = 0,

) : Parcelable