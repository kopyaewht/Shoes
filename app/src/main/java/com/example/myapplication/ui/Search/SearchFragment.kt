package com.example.myapplication.ui.Search

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.EvenBus.DataNotify
import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.Post
import com.example.myapplication.Model.ShopPost
import com.example.myapplication.R
import com.example.myapplication.Utli.DataManager
import com.example.myapplication.Utli.NetworkChecker
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.ui.PostDetailActivity
import com.example.myapplication.ui.home.HomeViewModel
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    companion object {
        fun newInstance() = SearchFragment()
    }
    private var _binding: FragmentSearchBinding? = null

    private val binding get() = _binding!!
    private lateinit var post_adapter: PostAdapter

    private var postlist = ArrayList<Post>()

    @Inject
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val root: View = binding.root

        post_adapter = PostAdapter(requireContext(),postlist)
        val postlayoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.postRecycleview.layoutManager = postlayoutManager

        binding.postRecycleview.adapter = post_adapter

        if (NetworkChecker().isInternetConnected(requireActivity())){
            viewModel.GetSearchHistoryList()
        }else{
            Toast.makeText(requireContext(),"Please connect Internet!", Toast.LENGTH_SHORT).show()
        }

        viewModel.posts.observe(viewLifecycleOwner){

            postlist.clear()
            postlist.addAll(it.post)

            post_adapter.notifyDataSetChanged()
        }
        post_adapter.onItemClick = {
            var intent = Intent(activity, PostDetailActivity::class.java)
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

            // post_adapter.notifyDataSetChanged()
        }

        post_adapter.onShoppingClick = {
            if (!DataManager.shopList.contains(ShopPost(postlist[it].id,postlist[it].name,postlist[it].price,1))){
                DataManager.shopList.add(ShopPost(postlist[it].id,postlist[it].name,postlist[it].price,1))
                EventBus.getDefault().post(DataNotify(1))
            }else{
                Toast.makeText(requireContext(),"Already Added to Cart", Toast.LENGTH_SHORT).show()
            }
            // post_adapter.notifyDataSetChanged()
        }

        return  root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

}