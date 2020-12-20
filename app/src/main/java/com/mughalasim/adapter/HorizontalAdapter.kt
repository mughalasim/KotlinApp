package com.mughalasim.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.network.model.ItemDataModel
import com.mughalasim.utils.Shared.Companion.getTimeFromMinutes
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_horizontal.view.*

class HorizontalAdapter(private val context: Context, private val items: List<ItemDataModel>?) :
    RecyclerView.Adapter<HorizontalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(items?.get(position))
    }

    override fun getItemCount(): Int {
        return items?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.list_item_horizontal, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(item: ItemDataModel?) {
            itemView.txt_video_title.text = item?.metaData?.title
            itemView.txt_video_desc.text = item?.metaData?.body
            // TODO: make this a nice time format
            itemView.txt_video_time.text = (item?.metaData?.VideoDuration).toString()

            itemView.progress_bar.visibility = View.VISIBLE
            Picasso.with(itemView.context).load(item?.mediaData?.thumbnailUrl)
                .into(itemView.image_video, object : Callback {
                    override fun onError() {
                        //Show an error here or a place holder image
                    }

                    override fun onSuccess() {
                        // We have the image, hide the progress bar
                        itemView.progress_bar.visibility = View.GONE
                    }
                })
        }
    }
}