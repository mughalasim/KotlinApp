package com.mughalasim.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.adapter.Adapter
import com.mughalasim.databinding.ActivityMainBinding
import com.mughalasim.model.MainActivityViewModel
import com.mughalasim.utils.Shared

class MainActivity : AppCompatActivity(), Adapter.CallbackInterface {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Bind the view and view model, setup the recycler view, its adapter and layout manager
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        binding.field = viewModel
        binding.lifecycleOwner = this
        val linearLayoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.layoutManager = linearLayoutManager
        binding.recyclerView.adapter = Adapter(this, this)

        // When retry is tapped, set the page number to the default and call the API
        binding.btnRetry.setOnClickListener {
            viewModel.pageNumber = Shared.DEFAULT_PAGE_NUMBER
            viewModel.getDataFromAPI()
        }

        // Observe changes in data and set them to the adapter
        viewModel.dataOneModel.observe(this, {
            (binding.recyclerView.adapter as Adapter).addData(it.children)
            viewModel.showDialog.value = it.children.isEmpty()
        })

        // If there is more data, increase the page number by 1 and fetch it automatically
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = linearLayoutManager.itemCount
                val lastVisible = linearLayoutManager.findLastVisibleItemPosition()
                val endHasBeenReached = lastVisible + 5 >= totalItemCount
                if (totalItemCount > 0 && endHasBeenReached && viewModel.hasNext.value == true
                    && viewModel.showLoading.value == false && viewModel.canLoadMore.value == false) {
                /* Call the API to load more ONLY when
                1. User has reached to the bottom of your recycler view,
                2. User can load more
                3. App is not currently loading previous results
                4. Not showing the option to load more
                 */
                    viewModel.getDataFromAPI()
                }
            }
        })

        // Will observe for the possibility to show a load more option in the event not all data was fetched
        viewModel.canLoadMore.observe(this, {
            if (it) {
                (binding.recyclerView.adapter as Adapter).showCanLoadMore(true)
            }
        })

        // Will observe if the screen is supposed to show a loading state or not
        viewModel.showLoading.observe(this, {
            (binding.recyclerView.adapter as Adapter).showLoading(it)
        })

        // Inform the user if loading failed half way or any other error
        viewModel.showToast.observe(this, {
            if (it != "") {
                toast(it)
            }
        })

        // Call the API
        viewModel.getDataFromAPI()

    }

    override fun onPause() {
        // If the activity is paused while loading, then remove the loading bar
        if (viewModel.showLoading.value == true) {
            (binding.recyclerView.adapter as Adapter).showLoading(false)
        }
        super.onPause()
    }

    override fun loadMore() {
        viewModel.getDataFromAPI()
    }

    private fun Context.toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()


}
