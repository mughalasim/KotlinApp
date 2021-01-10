package com.mughalasim

import com.mughalasim.network.ApiInterface
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.junit.Test


class APITest {

    // These are ver generic tests to check if the API works, ofoucrse If i had more time i would
    // write specific test to check things like data types, resources returned, or how the API would behave with wrong data sent

    // Check if the API is live
    @Test
    fun `Does it return a 200` () {
        // call the api for the first page
        val apiResponse = ApiInterface.create().getResult(1).execute()
        // Check if the API responds with a status cod of 200
        assertEquals(apiResponse.code(), 200)
    }

    // Check if the API handles invalid data sent
    @Test
    fun `Does it return a 404` () {
        // call the api for an invalid page
        val apiResponse = ApiInterface.create().getResult(-1).execute()
        // Check if the API responds with a status code of 404
        assertEquals(apiResponse.code(), 404)
    }

    // Check if the data returned isn't null
    @Test
    fun `Data returned on the first page is not empty or null` () {
        // call the api for the first page
        val apiResponse = ApiInterface.create().getResult(1).execute()
        // Check if the API responds with no null data on the first page
        apiResponse.body()?.results?.isEmpty()?.let { assertTrue(it) }
    }

    // Check if there is more than one data element returned in the array
    @Test
    fun `Does it return more than 0 people` () {
        // call the api for the first page
        val apiResponse = ApiInterface.create().getResult(1).execute()
        // Check if the API responds with some elements
        assertTrue(apiResponse.body()?.results?.count()!! > 0)
    }

}