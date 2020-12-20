package com.mughalasim.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.network.model.SectionsModel
import com.mughalasim.utils.Shared.Companion.MAX_HORIZONTAL_ITEMS
import kotlinx.android.synthetic.main.list_item_vertical.view.*

class VerticalAdapter(private val context: Context, private val list: List<SectionsModel>?) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(list?.get(position))
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_vertical, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(section: SectionsModel?) {
            itemView.txt_title.text = section?.name
            itemView.horizontal_recycler_view.layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
            val count = section?.itemData?.size
            if (count != null) {
                if (count < MAX_HORIZONTAL_ITEMS){
                    itemView.ll_view_all.visibility = View.GONE
                } else {
                    itemView.ll_view_all.visibility = View.VISIBLE
                }
                // We only want to show say 6 items only
                itemView.horizontal_recycler_view.visibility = View.VISIBLE
                itemView.horizontal_recycler_view.adapter = HorizontalAdapter(itemView.context, section.itemData?.take(MAX_HORIZONTAL_ITEMS))
            } else {
                itemView.ll_view_all.visibility = View.GONE
                itemView.txt_message.text = "No sub categories found in this section"
                itemView.horizontal_recycler_view.visibility = View.GONE
            }

        }
    }
}