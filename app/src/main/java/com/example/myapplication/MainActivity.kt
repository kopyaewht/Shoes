package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.EvenBus.DataNotify
import com.example.myapplication.Utli.DataManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.AddToCartActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        binding.cartbtn.setOnClickListener {
            startActivity(Intent(this,AddToCartActivity::class.java))
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications,R.id.navigation_search
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val menuItem1 = navView.menu.findItem(R.id.navigation_home)
        val lineDrawable = ContextCompat.getDrawable(this, R.drawable.triangle_circle)
        menuItem1.actionView?.setBackgroundResource(R.drawable.house)
        menuItem1.actionView?.background = lineDrawable

    }

    override fun onResume() {
        super.onResume()
        if (DataManager.shopList.size > 0){
            binding.count.text = DataManager.shopList.size.toString()
            binding.notiHolder.visibility = View.VISIBLE
        }else{
            binding.notiHolder.visibility = View.GONE
        }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: DataNotify?) {
        if (DataManager.shopList.size > 0){
            binding.count.text = DataManager.shopList.size.toString()
            binding.notiHolder.visibility = View.VISIBLE
            var shake = AnimationUtils.loadAnimation(this,R.anim.shake2)
            binding.notiHolder.startAnimation(shake)
        }else{
            binding.notiHolder.visibility = View.GONE
        }
    }
}