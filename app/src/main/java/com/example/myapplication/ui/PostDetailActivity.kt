package com.example.myapplication.ui

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.Model.Post
import com.example.myapplication.Model.ShopPost
import com.example.myapplication.Model.Sizelist
import com.example.myapplication.R
import com.example.myapplication.RecycleViewAdapter.OnboardingAdapter
import com.example.myapplication.RecycleViewAdapter.ViewPagerAdapter
import com.example.myapplication.Utli.DataManager
import com.example.myapplication.databinding.PostdetailactivityBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.ShoeSizeAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PostDetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding:PostdetailactivityBinding
    lateinit var viewPagerAdapter: ViewPagerAdapter
    lateinit var imageList: List<Int>
    private lateinit var shoeSizeAdapter: ShoeSizeAdapter
    var postdata:Post? = null


    private var sizelist = ArrayList<Sizelist>()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PostdetailactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }

        postdata = intent.getSerializableExtra("data") as Post

        SetupView(postdata!!)
        SetupImageBoard(postdata!!)




        shoeSizeAdapter.onItemClick = {
            sizelist.forEachIndexed { index, data ->
                if (index == it){
                    sizelist[index].select = 1;
                }else{
                    sizelist[index].select = 0;
                }
            }
            shoeSizeAdapter.notifyDataSetChanged()

        }



        binding.backbtn.setOnClickListener(this)
        binding.clickdesc.setOnClickListener(this)
        binding.clickfreeshipping.setOnClickListener(this)
        binding.clickheart.setOnClickListener(this)
        binding.addtocartbtn.setOnClickListener(this)
        binding.clickminus.setOnClickListener(this)
        binding.clickplus.setOnClickListener(this)

        binding.clickwhite.setOnClickListener(this)
        binding.clickblack.setOnClickListener(this)
        binding.clickgreen.setOnClickListener(this)
        binding.clickblue.setOnClickListener(this)


    }

    private fun SetupImageBoard(postdata: Post) {
        binding.viewpager.adapter = OnboardingAdapter(postdata!!.iconUrl);

        binding.apply {
            TabLayoutMediator(intoTabLayout, binding.viewpager) { tab, position -> }.attach() //The Magical Line

        }
    }

    private fun SetupView(postdata: Post) {
      binding.type.text = postdata.type
        binding.name.text= postdata.name
        binding.price.text = postdata.price
        binding.country.text = postdata.country
        binding.desc.text = postdata.desc
        binding.freeshipping.text = postdata.freedelivery

        sizelist.addAll(postdata!!.sizelist)
        shoeSizeAdapter = ShoeSizeAdapter(this,sizelist)
        val layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)
        binding.shoesizerecycleview.layoutManager = layoutManager
        binding.shoesizerecycleview.adapter = shoeSizeAdapter




    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.backbtn -> {
                finish()
            }

            R.id.clickfreeshipping -> {
               if (binding.freeshipping.isVisible){
                   binding.freeshipping.visibility = View.GONE
               }else{
                   binding.freeshipping.visibility = View.VISIBLE
                   binding.freeshipping.animation =
                       AnimationUtils.loadAnimation(binding.freeshipping.context,R.anim.scale)
               }
            }

            R.id.clickdesc -> {
                if (binding.desc.isVisible){
                    binding.desc.visibility = View.GONE
                }else{
                    binding.desc.visibility = View.VISIBLE
                    binding.desc.animation =
                        AnimationUtils.loadAnimation(binding.desc.context,R.anim.scale)
                }
            }
            R.id.clickheart -> {
                ShakeAnimation(binding.hearticon)

                if (postdata!!.like == 0){
                    postdata!!.like = 1
                    DataManager.dataList.add(postdata!!)
                    binding.hearticon.setColorFilter(ContextCompat.getColor(this, R.color.orange), PorterDuff.Mode.SRC_IN)

                }else{
                    postdata!!.like = 0
                    DataManager.dataList.forEachIndexed { index, post ->
                        if (post.id == postdata!!.id){
                            DataManager.dataList.removeAt(index)
                        }
                    }
                    binding.hearticon.setColorFilter(ContextCompat.getColor(this, R.color.black), PorterDuff.Mode.SRC_IN)

                }
            }

            R.id.addtocartbtn -> {

                DataManager.shopList.forEachIndexed { index, shopPost ->
                    if (postdata!!.id == shopPost.id){
                        DataManager.shopList.removeAt(index)
                    }else{

                    }
                }
                if (!DataManager.shopList.contains(ShopPost(postdata!!.id,postdata!!.name,postdata!!.price,binding.quantity.text.toString().toInt()))){
                    DataManager.shopList.add(ShopPost(postdata!!.id,postdata!!.name,postdata!!.price,binding.quantity.text.toString().toInt()))
                }else{
                    Toast.makeText(this,"Already Added to Cart", Toast.LENGTH_SHORT).show()
                }
                ShakeAnimation(binding.addtocartbtn)

                binding.addtocartbtn.setText("Added to Cart")
            }
            R.id.clickplus -> {
                ShakeAnimation(binding.clickplus)
                binding.quantity.text = (binding.quantity.text.toString().toInt() + 1).toString()
            }
            R.id.clickminus -> {
                ShakeAnimation(binding.clickminus)

                if(binding.quantity.text.toString().toInt() > 0){
                    binding.quantity.text = (binding.quantity.text.toString().toInt() - 1).toString()
                }else{
                }
            }
            R.id.clickwhite -> {
                ShakeAnimation(binding.clickwhite)
                binding.whiteicon.visibility = View.VISIBLE
                binding.blackicon.visibility = View.GONE
                binding.greenicon.visibility = View.GONE
                binding.blueicon.visibility = View.GONE

            }

            R.id.clickblack -> {
                ShakeAnimation(binding.clickblack)
                binding.whiteicon.visibility = View.GONE
                binding.blackicon.visibility = View.VISIBLE
                binding.greenicon.visibility = View.GONE
                binding.blueicon.visibility = View.GONE

            }

            R.id.clickgreen -> {
                ShakeAnimation(binding.clickgreen)

                binding.whiteicon.visibility = View.GONE
                binding.blackicon.visibility = View.GONE
                binding.greenicon.visibility = View.VISIBLE
                binding.blueicon.visibility = View.GONE

            }

            R.id.clickblue -> {
                ShakeAnimation(binding.clickblue)

                binding.whiteicon.visibility = View.GONE
                binding.blackicon.visibility = View.GONE
                binding.greenicon.visibility = View.GONE
                binding.blueicon.visibility = View.VISIBLE

            }

        }
    }



    private fun ShakeAnimation(view: View) {
        var shake = AnimationUtils.loadAnimation(this,R.anim.shake2)
        view.startAnimation(shake)
    }
}