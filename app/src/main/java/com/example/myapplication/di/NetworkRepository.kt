package com.example.myapplication.di



import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.ResponseData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import javax.inject.Inject

class NetworkRepository @Inject constructor(private val service: NetworkService) {

     fun PostList(): Call<ResponseData> {
        return service.BeerList()
    }





}