package com.example.coroutinesflowdemo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.coroutinesflowdemo.R
import com.example.coroutinesflowdemo.model.GirlResp
import kotlinx.android.synthetic.main.listitem_girl_item.view.*

class GirlAdapter: PagingDataAdapter<GirlResp, GirlAdapter.GirlHolder>(GirlComparator) {
    private var context: Context? = null
    override fun onBindViewHolder(holder: GirlHolder, position: Int) {
        Glide.with(context!!)
            .load(getItem(position)?.url ?: "")
            .into(holder.itemView.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GirlHolder {
        context = parent.context
        return GirlHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.listitem_girl_item, parent, false)
        )
    }

    class GirlHolder(view: View) : RecyclerView.ViewHolder(view)

    object GirlComparator : DiffUtil.ItemCallback<GirlResp>() {
        override fun areItemsTheSame(oldItem: GirlResp, newItem: GirlResp): Boolean {
            // Id is unique.
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: GirlResp, newItem: GirlResp): Boolean {
            return oldItem == newItem
        }
    }
}