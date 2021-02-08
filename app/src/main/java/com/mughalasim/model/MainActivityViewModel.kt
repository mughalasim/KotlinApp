package com.mughalasim.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mughalasim.network.ApiInterface
import com.mughalasim.network.model.DataOneModel
import com.mughalasim.network.model.MainDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    var txtErrorMessage: MutableLiveData<String> = MutableLiveData<String>("")
    var showDialog: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var showToast: MutableLiveData<String> = MutableLiveData<String>("")
    var hasNext: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var canLoadMore: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var showLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var pageNumber: String? = null
    var dataOneModel: MutableLiveData<DataOneModel> = MutableLiveData(DataOneModel())

    fun getDataFromAPI() {
        // Clear previous dialogs and show a loading screen
        showLoading.postValue(true)
        showDialog.postValue(false)

        ApiInterface.create().getResult(pageNumber).enqueue(object : Callback<MainDataModel> {

            override fun onFailure(call: Call<MainDataModel>?, t: Throwable?) {
                // Clear the loading progress bar
                showLoading.postValue(false)
                // If the user was not able to load the first page then show a large dialog
                if (pageNumber == null){
                    txtErrorMessage.postValue("Failed to load data: " + t.toString())
                    showDialog.postValue(true)
                } else {
                    // The first page was loaded so it must have been a connection issue at this point
                    showToast.postValue("Failed to load more results")
                    canLoadMore.postValue(true)
                }
            }

            override fun onResponse(call: Call<MainDataModel>?, response: Response<MainDataModel>?) {
                // Clear the loading progress bar
                showLoading.postValue(false)

                val code = response?.code()

                if (code != 200 || response.body() == null) {
                    txtErrorMessage.postValue("Error response from the server: $code")
                    showDialog.postValue(true)
                    return
                }

                // Post results received from the api
                dataOneModel.postValue(response.body()?.data ?: DataOneModel())

                // If the list is empty, show a message
                if (response.body()?.data?.children?.size ?: 0 < 1) {
                    if (pageNumber == ""){
                        txtErrorMessage.postValue("No data found, You may retry")
                        showDialog.postValue(true)
                    }
                }

                // If there is more data to load set that here
                hasNext.postValue(response.body()?.data?.after != null)

            }
        })
    }
}
