package com.mughalasim.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mughalasim.network.ApiInterface
import com.mughalasim.network.model.PeopleModel
import com.mughalasim.network.model.ResultModel
import com.mughalasim.utils.Shared
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var txtErrorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    var showDialog: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var showToast: MutableLiveData<String> = MutableLiveData<String>("")
    var hasNext: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var showLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var pageNumber: Int = Shared.DEFAULT_PAGE_NUMBER
    var peopleList: MutableLiveData<List<PeopleModel>> = MutableLiveData(listOf())

    fun getDataFromAPI() {
        showLoading.postValue(true)

        ApiInterface.create().getResult(pageNumber).enqueue(object : Callback<ResultModel> {

            override fun onFailure(call: Call<ResultModel>?, t: Throwable?) {
                showLoading.postValue(false)
                if (pageNumber == 1){
                    txtErrorMessage.postValue("Failed to load data: " + t.toString())
                    showDialog.postValue(true)
                } else {
                    showToast.postValue("Failed to load more results")
                }
            }

            override fun onResponse(call: Call<ResultModel>?, response: Response<ResultModel>?) {
                showLoading.postValue(false)
                showDialog.postValue(false)

                val code = response?.code()

                if (code != 200 || response.body() == null) {
                    txtErrorMessage.postValue("Error response from the server: $code")
                    showDialog.postValue(true)
                    return
                }

                // Post results received from the api
                peopleList.postValue(response.body()?.results ?: listOf())

                // If the list is empty, show a message
                if (peopleList.value?.size ?: 0 < 1) {
                    if(pageNumber == 1){
                        txtErrorMessage.postValue("No data found, You may retry")
                        showDialog.postValue(true)
                    } else {
                        showToast.postValue("No more results returned")
                    }
                }

                // If there is more data to load set that here
                // The list items where only ten so I decided to call the API till the last page
                // Obviously would be have been better to call first 4 pages first then let the scroll listener
                // detect when the user has reached the end to load more
                hasNext.postValue(response.body()?.next != null)

            }
        })
    }
}
