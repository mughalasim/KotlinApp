package com.mughalasim.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.databinding.ListItemHorizontalBinding
import com.mughalasim.network.model.ItemDataModel
import com.mughalasim.utils.Shared.Companion.getTimeFromMinutes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class HorizontalAdapter(private val context: Context, private val items: List<ItemDataModel>?) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            val item = items?.get(position)
            binding.txtVideoTitle.text = item?.metaData?.title
            binding.txtVideoDesc.text = item?.metaData?.body
            binding.txtVideoTime.text = getTimeFromMinutes(item?.metaData?.VideoDuration ?: 0)

            binding.progressBar.visibility = View.VISIBLE
            Picasso.with(context).load(item?.mediaData?.thumbnailUrl)
                .into(binding.imageVideo, object : Callback {
                    override fun onError() {
                        //Show an error here or a place holder image
                    }

                    override fun onSuccess() {
                        // We have the image, hide the progress bar
                        binding.progressBar.visibility = View.GONE
                    }
                })
        }
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_horizontal, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ListItemHorizontalBinding.bind(view)
    }
}