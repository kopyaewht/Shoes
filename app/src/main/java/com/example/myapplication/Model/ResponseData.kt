package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("status"   ) var status   : Int?                = null,
    @SerializedName("message"  ) var message  : String?             = null,
    @SerializedName("category" ) var category : ArrayList<CategoryObj> = arrayListOf(),
    @SerializedName("post"     ) var post     : ArrayList<Post>     = arrayListOf()
)
