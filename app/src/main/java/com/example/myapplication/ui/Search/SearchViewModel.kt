package com.example.myapplication.ui.Search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Model.Post
import com.example.myapplication.Model.ResponseData
import com.example.myapplication.di.NetworkRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val networkRepository: NetworkRepository): ViewModel() {

    private val _posts = MutableLiveData<ResponseData>()
    val posts: LiveData<ResponseData> = _posts
    private val _likes = MutableLiveData<List<Post>>()

    var likepost: MutableList<List<Post>> = mutableListOf()

    fun GetSearchHistoryList() {
        try {
            val userList = networkRepository.PostList()
            userList.enqueue(object : Callback<ResponseData> {
                override fun onResponse(call: Call<ResponseData>, response: Response<ResponseData>) {
                    if (response.isSuccessful){
                        _posts.postValue(response.body())
                        likepost.addAll(listOf(response.body()!!.post))
                    }

                }

                override fun onFailure(call: Call<ResponseData>, t: Throwable) {

                }

            })

        } catch (e: Exception) {
            // Handle error

        }
    }

}