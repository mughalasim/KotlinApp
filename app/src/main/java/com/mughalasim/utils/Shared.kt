package com.mughalasim.utils

import java.text.DecimalFormat

class Shared {
    companion object {
        @JvmField
        val BASE_URL = "https://swapi.dev/api/"

        @JvmField
        val TAG = "DEBUG APP"

        @JvmField
        val DEFAULT_PAGE_NUMBER = 1

        fun getTimeFromMinutes(time: Int): String {
            val dec = DecimalFormat("00")

            var n = time % (24 * 3600)
            val hour: Int = n / 3600

            n %= 3600
            val minutes: Int = n / 60

            n %= 60
            val seconds: Int = n

            return "${dec.format(hour)}:${dec.format(minutes)}:${dec.format(seconds)}"
        }
    }
}