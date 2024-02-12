package com.example.myapplication.ui.Remark

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.EvenBus.DataNotify
import com.example.myapplication.Model.Post
import com.example.myapplication.Model.ShopPost
import com.example.myapplication.Utli.DataManager
import com.example.myapplication.databinding.FragmentNotificationsBinding
import com.example.myapplication.ui.PostDetailActivity
import com.example.myapplication.ui.home.HomeViewModel
import com.kintel.shwemyanmar2d3d.RecycleviewAdapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import org.greenrobot.eventbus.EventBus
import javax.inject.Inject

@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    @Inject
    lateinit var viewModel: HomeViewModel

    private lateinit var post_adapter:PostAdapter

    private var postlist = ArrayList<Post>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        postlist.addAll(DataManager.dataList)
        post_adapter = PostAdapter(requireContext(),postlist)
        val postlayoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
        binding.postRecycleview.layoutManager = postlayoutManager

        binding.postRecycleview.adapter = post_adapter

        if (postlist.size > 0){
            binding.nodata.visibility = View.GONE
        }else{
            binding.nodata.visibility = View.VISIBLE

        }

        post_adapter.onItemClick = {
            var intent = Intent(activity, PostDetailActivity::class.java)
            var bundle = Bundle()
            bundle.putSerializable("data",it)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        post_adapter.onLikeClick = {

            DataManager.dataList.removeAt(it)
            postlist.removeAt(it)
            post_adapter.notifyDataSetChanged()
        }

        post_adapter.onShoppingClick = {
            if (!DataManager.shopList.contains(ShopPost(postlist[it].id,postlist[it].name,postlist[it].price,1))){
                DataManager.shopList.add(ShopPost(postlist[it].id,postlist[it].name,postlist[it].price,1))
                EventBus.getDefault().post(DataNotify(1))
            }else{
                Toast.makeText(requireContext(),"Already Added to Cart",Toast.LENGTH_SHORT).show()
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}