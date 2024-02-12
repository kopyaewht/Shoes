package com.example.myapplication.di



import com.example.myapplication.Model.CategoryObj
import com.example.myapplication.Model.ResponseData
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface NetworkService {

     @GET("codetest")
     fun BeerList(): Call<ResponseData>



}