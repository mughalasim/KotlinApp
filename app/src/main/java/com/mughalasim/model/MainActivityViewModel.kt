package com.mughalasim.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mughalasim.network.ApiInterface
import com.mughalasim.network.model.ResultModel
import com.mughalasim.network.model.SectionsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var txtLoadingMessage: MutableLiveData<String> = MutableLiveData<String>("")
    var noResults: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var verticalList: MutableLiveData<List<SectionsModel>> = MutableLiveData(listOf())

    fun getDataFromAPI() {
        // Call the API
        ApiInterface.create().getResult().enqueue(object : Callback<ResultModel> {
            // Handle success and failure here from the API although Status code and response type can be handled elsewhere
            // In the API Interface

            override fun onFailure(call: Call<ResultModel>?, t: Throwable?) {
                txtLoadingMessage.postValue("Failed to load data, Please check your internet connection")
            }

            override fun onResponse(call: Call<ResultModel>?, response: Response<ResultModel>?) {
                txtLoadingMessage.postValue("")
                verticalList.postValue(response?.body()?.sections ?: listOf())
                if (verticalList.value?.size ?:0  < 1){
                    txtLoadingMessage.postValue("No results found")
                }
            }
        })
    }
}
