package com.kintel.shwemyanmar2d3d.RecycleviewAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.R
import com.squareup.picasso.Picasso


internal class CategoryAdapter(private var itemsList: List<CategoryObj>) :
    RecyclerView.Adapter<CategoryAdapter.MyViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null
    var onItemUnCheckClick: ((Int) -> Unit)? = null


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var icon: AppCompatImageView = view.findViewById(R.id.icon)
    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.category_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        Picasso.get().load(item.iconUrl).into(holder.icon)
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context,R.anim.scale)

    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}