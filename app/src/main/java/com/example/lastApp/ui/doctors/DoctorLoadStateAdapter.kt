package com.example.lastApp.ui.doctors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.lastApp.databinding.ItemListFooterBinding

class DoctorLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<DoctorLoadStateAdapter.DoctorStateViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState,
    ): DoctorStateViewHolder {
        val binding =
            ItemListFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorStateViewHolder(binding)
    }


    override fun onBindViewHolder(holder: DoctorStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }


    inner class DoctorStateViewHolder(private val binding: ItemListFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnRetryFooter.setOnClickListener {
                retry.invoke()
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBarFooter.isVisible = loadState is LoadState.Loading
                btnRetryFooter.isVisible = loadState !is LoadState.Loading
                tvRetryFooter.isVisible = loadState !is LoadState.Loading
            }
        }
    }

}


















