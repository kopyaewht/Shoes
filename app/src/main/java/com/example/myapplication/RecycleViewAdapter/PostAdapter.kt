package com.kintel.shwemyanmar2d3d.RecycleviewAdapter

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.Post
import com.example.myapplication.R
import com.squareup.picasso.Picasso


internal class PostAdapter(var context:Context,private var itemsList: List<Post>) :
    RecyclerView.Adapter<PostAdapter.MyViewHolder>() {

    var onItemClick: ((Post) -> Unit)? = null
    var onLikeClick: ((Int) -> Unit)? = null
    var onShoppingClick: ((Int) -> Unit)? = null


    internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var icon: AppCompatImageView = view.findViewById(R.id.icon)
        var price: AppCompatTextView = view.findViewById(R.id.price)
        var name: AppCompatTextView = view.findViewById(R.id.name)
        var hearticon: AppCompatImageView = view.findViewById(R.id.hearticon)
        var clickhearticon: RelativeLayout = view.findViewById(R.id.clickhearticon)
        var shoppingcarticon: AppCompatImageView = view.findViewById(R.id.shoppingcart)
        var clickshoppingcart: RelativeLayout = view.findViewById(R.id.clickshoppingcart)

    }

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.post_item, parent, false)
        return MyViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val item = itemsList[position]
        holder.itemView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context,R.anim.scale)
        if (item.iconUrl.size > 0){
            Picasso.get().load(item.iconUrl[0].icon).into(holder.icon)
        }
        holder.price.text = item.price
        holder.name.text = item.name

        holder.icon.setOnClickListener {
            onItemClick?.invoke(item)
        }

        if (item.like == 0){
            holder.hearticon.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)

        }else{
            holder.hearticon.setColorFilter(ContextCompat.getColor(context, R.color.orange), PorterDuff.Mode.SRC_IN)

        }

        holder.clickhearticon.setOnClickListener {
            var shake = AnimationUtils.loadAnimation(context,R.anim.shake)
            holder.hearticon.startAnimation(shake)
            if (item.like == 0){
                holder.hearticon.setColorFilter(ContextCompat.getColor(context, R.color.orange), PorterDuff.Mode.SRC_IN)
            }else{
                holder.hearticon.setColorFilter(ContextCompat.getColor(context, R.color.black), PorterDuff.Mode.SRC_IN)

            }
            onLikeClick?.invoke(position)
        }

        holder.clickshoppingcart.setOnClickListener {
            var shake = AnimationUtils.loadAnimation(context,R.anim.shake)
            holder.shoppingcarticon.startAnimation(shake)
            onShoppingClick?.invoke(position)
        }



    }
    override fun getItemCount(): Int {
        return itemsList.size
    }
}