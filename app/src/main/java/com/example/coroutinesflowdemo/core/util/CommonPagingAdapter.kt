package com.example.coroutinesflowdemo.core.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by james.li on 2020/9/11
 * Description:
 */

open class CommonPagingAdapter<T: Any>(comparator: DiffUtil.ItemCallback<T>,
                                       private val layoutIds: Array<Int>, private val modelTypes: Array<Class<*>>,
                                       val bindItem: (itemView: View, model: T?, position: Int) -> Unit,
                                       private val onItemClick: ((model: T?, position: Int) -> Unit))
    : PagingDataAdapter<T, RecyclerView.ViewHolder>(comparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        var layoutId = 0
        for (index in modelTypes.indices) {
            val classType = modelTypes[index].simpleName
            val dataType = getItem(position)?.javaClass?.simpleName
            //Log.d("test", "index= $index classType= $classType position= $position classType= ${dataList[position].javaClass.simpleName}")
            if (classType == dataType) {
                layoutId = layoutIds[index]
                break
            }
        }
        require(layoutId != 0) { "it can't find this type's layout" }
        return layoutId
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        bindItem(holder.itemView, getItem(position), position)
        holder.itemView.setOnClickListener { onItemClick.invoke(getItem(position), position) }
    }

    inner class RecyclerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

}
