package com.example.coroutinesflowdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutinesflowdemo.R
import kotlinx.android.synthetic.main.listitem_load_state_view.view.*

/**
 * Create by james.li on 2020/9/11
 * Description:
 */

class GirlLoadStateAdapter(private val retry: () -> Unit): LoadStateAdapter<GirlLoadStateAdapter.GirlLoadStateHolder>() {

    override fun onBindViewHolder(holder: GirlLoadStateHolder, loadState: LoadState) = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): GirlLoadStateHolder = GirlLoadStateHolder(parent, retry)

    class GirlLoadStateHolder(
        parent: ViewGroup,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_load_state_view, parent, false)
    ) {

        private val progressBar: ProgressBar = itemView.load_state_progress
        private val errorMsg: TextView = itemView.load_state_errorMessage
        private val retry: Button = itemView.load_state_retry
            .also {
                it.setOnClickListener { retry() }
            }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                errorMsg.text = "loading error"
            }
            if (loadState.endOfPaginationReached) {
                progressBar.isVisible = false
                retry.isVisible = false
                errorMsg.isVisible = false
            } else {
                progressBar.isVisible = loadState is LoadState.Loading
                retry.isVisible = loadState is LoadState.Error
                errorMsg.isVisible = loadState is LoadState.Error
            }
        }
    }

}