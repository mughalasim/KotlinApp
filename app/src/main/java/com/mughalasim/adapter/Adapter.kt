package com.mughalasim.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mughalasim.R
import com.mughalasim.databinding.ListItemBinding
import com.mughalasim.network.model.PeopleModel
import com.mughalasim.ui.DescActivity

class Adapter(private val context: Context, private val callbackInterface: CallbackInterface) :
    RecyclerView.Adapter<Adapter.ViewHolder>() {

    interface CallbackInterface {
        fun loadMore()
    }

    private var list: List<PeopleModel>? = listOf()

    fun addData(moreData: List<PeopleModel>) {
        list = list?.plus(moreData)
        this.notifyDataSetChanged()
    }

    fun showLoading(show: Boolean) {
        // Add a loading progress bar under the last list item while fetching more.
        list = if (show) {
            list?.plus(PeopleModel(is_loading = true, can_load_more = false))
        } else {
            list?.dropLastWhile { it.is_loading == true }
        }
        this.notifyDataSetChanged()
    }

    fun showCanLoadMore(show: Boolean) {
        // Add a load more button under the last list item when there is the option to load more
        list = if (show) {
            list?.plus(PeopleModel(is_loading = false, can_load_more = true))
        } else {
            list?.dropLastWhile { it.can_load_more == true }
        }
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            if (list?.get(position)?.is_loading == true) {
                binding.progress.visibility = View.VISIBLE
            } else {
                binding.progress.visibility = View.GONE
            }

            if (list?.get(position)?.can_load_more == true) {
                binding.btnLoadMore.visibility = View.VISIBLE
            } else {
                binding.btnLoadMore.visibility = View.GONE
            }

            binding.txtName.text = list?.get(position)?.name

            binding.btnLoadMore.setOnClickListener {
                showCanLoadMore(false)
                // Created an interface that will inform the activity to re-fetch the remaining results
                callbackInterface.loadMore()
            }

            binding.txtName.setOnClickListener {
                // If the data isn't present do not take the user to the next activity
                if (list?.get(position) == null) {
                    return@setOnClickListener
                }
                val intent = Intent(context, DescActivity::class.java)
                // Pass data to next activity as a parsable
                intent.putExtra("person", list?.get(position))
                context.startActivity(intent)
            }
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