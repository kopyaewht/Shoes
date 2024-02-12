package com.example.myapplication.ui.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.EvenBus.DataNotify
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.Post
import com.example.myapplication.Model.ShopPost
import com.example.myapplication.MyApplication
import com.example.myapplication.R
import com.example.myapplication.Utli.DataManager
import com.example.myapplication.Utli.NetworkChecker
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.ui.PostDetailActivity
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.CategoryAdapter
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var category_adapter:CategoryAdapter
    private lateinit var post_adapter:PostAdapter

    private var categorylist = ArrayList<CategoryObj>()
    private var postlist = ArrayList<Post>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //Category
        category_adapter = CategoryAdapter(categorylist)
        val layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.categoryRecycleview.layoutManager = layoutManager
        binding.categoryRecycleview.adapter = category_adapter

        //Popular brand
        post_adapter = PostAdapter(requireContext(),postlist)
        val postlayoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
        binding.postRecycleview.layoutManager = postlayoutManager

        binding.postRecycleview.adapter = post_adapter

        if (NetworkChecker().isInternetConnected(requireActivity())){
            viewModel.GetPostList()
        }else{
            Toast.makeText(requireContext(),"Please connect Internet!",Toast.LENGTH_SHORT).show()
        }

        viewModel.posts.observe(viewLifecycleOwner){
            categorylist.clear()
            categorylist.addAll(it.category)
            category_adapter.notifyDataSetChanged()
            postlist.clear()
            postlist.addAll(it.post)

            post_adapter.notifyDataSetChanged()
        }
        post_adapter.onItemClick = {
            var intent = Intent(activity,PostDetailActivity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("data",it)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        post_adapter.onLikeClick = {
            if (postlist[it].like == 0){
                postlist[it].like = 1
                DataManager.dataList.add(postlist[it])
            }else{
                postlist[it].like = 0
                DataManager.dataList.forEachIndexed { index, post ->
                    if (post.id == postlist[it].id){
                        DataManager.dataList.removeAt(index)
                    }
                }
            }


        }

        post_adapter.onShoppingClick = {
            if (!DataManager.shopList.contains(ShopPost(postlist[it].id,postlist[it].name,postlist[it].price,1))){
                DataManager.shopList.add(ShopPost(postlist[it].id,postlist[it].name,postlist[it].price,1))
                EventBus.getDefault().post(DataNotify(1))
            }else{
                Toast.makeText(requireContext(),"Already Added to Cart",Toast.LENGTH_SHORT).show()
            }

        }

        binding.search.addTextChangedListener(textWatcher);



        return root
    }

    var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            // this function is called before text is edited
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            Toast.makeText(requireContext(),"This Feature is coming soon",Toast.LENGTH_SHORT).show()
        }

        override fun afterTextChanged(s: Editable) {
            // this function is called after text is edited
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}