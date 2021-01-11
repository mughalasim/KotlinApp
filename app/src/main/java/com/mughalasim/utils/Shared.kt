package com.mughalasim.utils

class Shared {
    companion object {
        // Base url of the API easier to manage API versions from a central location
        const val BASE_URL = "https://swapi.dev/api/"

        // Name of the parsable value to be sent to the next activity
        const val INTENT_EXTRA = "person"

        // The default page to start fetching data from
        const val DEFAULT_PAGE_NUMBER = 1

    }
}