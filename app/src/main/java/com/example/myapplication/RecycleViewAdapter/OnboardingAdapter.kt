package com.example.myapplication.RecycleViewAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.IconUrl
import com.example.myapplication.databinding.ViewpagerItemBinding
import com.squareup.picasso.Picasso

class OnboardingAdapter(val imageList: List<IconUrl>) : RecyclerView.Adapter<OnboardingAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewpagerItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ViewpagerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.apply {

                Picasso.get().load(imageList[position].icon).into(this.icon)


            }
        }
    }

    override fun getItemCount() = imageList.size
}