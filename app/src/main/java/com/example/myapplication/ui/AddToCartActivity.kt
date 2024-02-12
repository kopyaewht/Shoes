package com.example.myapplication.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.ShopPost
import com.example.myapplication.R
import com.example.myapplication.Utli.DataManager
import com.example.myapplication.databinding.AddtocartactivityBinding
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.AddToCartAdapter
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddToCartActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var binding: AddtocartactivityBinding
    private lateinit var addToCartAdapter: AddToCartAdapter

    private var datalist = ArrayList<ShopPost>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddtocartactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }

        datalist.addAll(DataManager.shopList)
        addToCartAdapter = AddToCartAdapter(this,datalist)
        val postlayoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)
        binding.postRecycleview.layoutManager = postlayoutManager
        binding.postRecycleview.adapter = addToCartAdapter

        addToCartAdapter.onMinusClick = {
            if (datalist[it].quantity!! > 0){
                datalist[it].quantity = datalist[it].quantity!! - 1
            }
            addToCartAdapter.notifyDataSetChanged()
        }

        addToCartAdapter.onPlusClick = {
            datalist[it].quantity =  datalist[it].quantity!! + 1
            addToCartAdapter.notifyDataSetChanged()
        }

        binding.clickbin.setOnClickListener(this)
        binding.addtocartbtn.setOnClickListener(this)
        binding.backbtn.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.clickbin -> {
                DataManager.shopList.clear()
                datalist.clear()
                addToCartAdapter.notifyDataSetChanged()
            }
            R.id.addtocartbtn -> {
                DataManager.shopList.clear()
                datalist.clear()
                Toast.makeText(this,"Thank you for buying",Toast.LENGTH_SHORT).show()
                finish()
            }
            R.id.backbtn -> {
                finish()
            }
        }
    }
}