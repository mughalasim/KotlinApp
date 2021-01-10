package com.mughalasim.model

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mughalasim.network.model.PeopleModel
import java.text.SimpleDateFormat

class DescActivityViewModel : ViewModel() {

    lateinit var people: PeopleModel
    var dateTime: MutableLiveData<String> = MutableLiveData<String>("")

    @SuppressLint("SimpleDateFormat")
    fun getDateAndTime(): String {
        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        return formatter.format(parser.parse(people.created))
    }
}
