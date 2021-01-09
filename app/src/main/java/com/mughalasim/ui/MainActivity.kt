package com.mughalasim.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mughalasim.R
import com.mughalasim.adapter.Adapter
import com.mughalasim.databinding.ActivityMainBinding
import com.mughalasim.model.MainActivityViewModel
import com.mughalasim.utils.Shared

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.field = viewModel
        binding.lifecycleOwner = this

        binding.recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = Adapter(applicationContext)

        binding.btnRetry.setOnClickListener {
            viewModel.pageNumber = Shared.DEFAULT_PAGE_NUMBER
            viewModel.getDataFromAPI()
        }

        viewModel.peopleList.observe(this, {
            Log.d(Shared.TAG, "Data received of size: " + it.count())
            (binding.recyclerView.adapter as Adapter).addData(it)
            viewModel.showDialog.value = it.isEmpty()
        })

        viewModel.hasNext.observe(this, {
            if (it) {
                viewModel.pageNumber++
                viewModel.getDataFromAPI()
            }
        })

        viewModel.showLoading.observe(this, {
            (binding.recyclerView.adapter as Adapter).showLoading(it)
        })

        viewModel.showToast.observe(this, {
            if (it != "") {
                toast(it)
            }
        })

        viewModel.getDataFromAPI()

    }

    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
