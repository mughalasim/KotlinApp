package com.mughalasim.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.databinding.ListItemVerticalBinding
import com.mughalasim.network.model.SectionsModel
import com.mughalasim.utils.Shared.Companion.MAX_HORIZONTAL_ITEMS

class VerticalAdapter(private val context: Context, private val list: List<SectionsModel>?) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.txtTitle.text =  list?.get(position)?.name
            binding.horizontalRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            val count = list?.get(position)?.itemData?.size
            if (count != null) {
                if (count < MAX_HORIZONTAL_ITEMS){
                    binding.llViewAll.visibility = View.GONE
                } else {
                    binding.llViewAll.visibility = View.VISIBLE
                }
                // We only want to show say 6 items only
                binding.horizontalRecyclerView.visibility = View.VISIBLE
                binding.horizontalRecyclerView.adapter = HorizontalAdapter(context,  list?.get(position)?.itemData?.take(MAX_HORIZONTAL_ITEMS))
            } else {
                binding.llViewAll.visibility = View.GONE
                binding.txtMessage.text = "No sub categories found in this section"
                binding.horizontalRecyclerView.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item_vertical, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemVerticalBinding.bind(view)
    }
}