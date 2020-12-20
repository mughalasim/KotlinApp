package com.mughalasim.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mughalasim.R
import com.mughalasim.adapter.VerticalAdapter
import com.mughalasim.databinding.ActivityMainBinding
import com.mughalasim.model.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.field = viewModel
        binding.lifecycleOwner = this

        binding.verticalRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)

        viewModel.verticalList.observe(this, {
            binding.verticalRecyclerView.adapter = VerticalAdapter(applicationContext, it)
            viewModel.noResults.value = it.isEmpty()
        })

        viewModel.txtLoadingMessage.postValue("Fetching results... please wait...")

        viewModel.getDataFromAPI()

    }
}
