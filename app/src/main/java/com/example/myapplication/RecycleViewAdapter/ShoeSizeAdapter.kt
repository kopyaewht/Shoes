package com.kintel.shwemyanmar2d3d.RecycleviewAdapter

import android.content.Context
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
import androidx.cardview.widget.CardView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.Sizelist
import com.example.myapplication.R
import com.squareup.picasso.Picasso


internal class ShoeSizeAdapter(var context:Context,private var itemsList: List<Sizelist>) :
    RecyclerView.Adapter<ShoeSizeAdapter.MyViewHolder>() {

    var onItemClick: ((Int) -> Unit)? = null
    var onItemUnCheckClick: ((Int) -> Unit)? = null


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: AppCompatTextView = view.findViewById(R.id.name)
        var card: CardView = view.findViewById(R.id.card)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.shoesize_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.name.text = item.size
        if (item.select == 0){
            holder.name.setTextColor(context.resources.getColor(R.color.black))
            holder.card.setCardBackgroundColor(context.resources.getColor(R.color.white_grey))
        }else{
            holder.name.setTextColor(context.resources.getColor(R.color.white))
            holder.card.setCardBackgroundColor(context.resources.getColor(R.color.orange))

        }
        holder.itemView.setOnClickListener {
            var shake = AnimationUtils.loadAnimation(context,R.anim.shake2)
            holder.itemView.startAnimation(shake)
            onItemClick?.invoke(position)
        }

    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}