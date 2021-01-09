package com.mughalasim.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.databinding.ListItemBinding
import com.mughalasim.network.model.PeopleModel

class Adapter(private val context: Context) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    private var list: List<PeopleModel>? = listOf()

    fun addData(moreData: List<PeopleModel>) {
        list = list?.plus(moreData)
        this.notifyDataSetChanged()
    }

    fun clearData() {
        list = listOf()
        this.notifyDataSetChanged()
    }

    fun showLoading(show: Boolean) {
        if (show) {
            list = list?.plus(PeopleModel("", "", "", "", true))
        } else {
            list = list?.dropLast(1)
        }
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (list?.get(position)?.load_more == true) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }
            binding.txtName.text = list?.get(position)?.name
        }
    }

    override fun getItemCount(): Int {
        return list?.size!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)
    }
}