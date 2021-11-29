package com.example.lastApp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.lastApp.R
import com.example.lastApp.databinding.DoctorItemRowBinding
import com.example.lastApp.models.doctor.Data

class DoctorAdapter : PagingDataAdapter<Data, DoctorAdapter.DoctorViewHolder>(DOCTOR_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding =
            DoctorItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val currItem = getItem(position)
        if (currItem != null) {
            holder.bind(currItem)
        }
    }

    inner class DoctorViewHolder(private val binding: DoctorItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(doctor: Data) {
            binding.apply {
                Glide.with(itemView)
                    .load(doctor.photo)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.doctor)
                    .into(imgDoctor)
                txDoctorName.text = doctor.name
                txDeptName.text = doctor.specialist
                txDoctorCity.text = doctor.city
            }
        }
    }

    companion object {
        private val DOCTOR_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Data, newItem: Data) =
                oldItem == newItem
        }
    }

}

























