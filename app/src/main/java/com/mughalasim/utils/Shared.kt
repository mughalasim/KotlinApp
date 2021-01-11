package com.mughalasim.utils

class Shared {
    companion object {
        // Base url of the API easier to manage API versions from a central location
        @JvmField
        val BASE_URL = "https://swapi.dev/api/"

        // For logging purposes
        @JvmField
        val TAG = "DEBUG APP"

        // The default page to start fetching data from
        @JvmField
        val DEFAULT_PAGE_NUMBER = 1

    }
}