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
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.ShopPost
import com.example.myapplication.R
import com.squareup.picasso.Picasso


internal class AddToCartAdapter(var context:Context,private var itemsList: List<ShopPost>) :
    RecyclerView.Adapter<AddToCartAdapter.MyViewHolder>() {

    var onMinusClick: ((Int) -> Unit)? = null
    var onPlusClick: ((Int) -> Unit)? = null

    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: AppCompatTextView = view.findViewById(R.id.name)
        var quantity: AppCompatTextView = view.findViewById(R.id.quantity)
        var clickminus: RelativeLayout = view.findViewById(R.id.clickminus)
        var clickplus: RelativeLayout = view.findViewById(R.id.clickplus)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.addtocart_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = itemsList[position]
        holder.name.text = item.name
        holder.quantity.text = item.quantity.toString()
//        holder.itemView.animation =
//            AnimationUtils.loadAnimation(holder.itemView.context,R.anim.scale)

        holder.clickminus.setOnClickListener {
            var shake = AnimationUtils.loadAnimation(context,R.anim.shake2)
            holder.clickminus.startAnimation(shake)
            onMinusClick?.invoke(position)

        }

        holder.clickplus.setOnClickListener {
            var shake = AnimationUtils.loadAnimation(context,R.anim.shake2)
            holder.clickplus.startAnimation(shake)
            onPlusClick?.invoke(position)
        }

    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}