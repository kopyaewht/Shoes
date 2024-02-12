package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName

data class CategoryObj(@SerializedName("id"       ) var id      : Int?    = null,
                       @SerializedName("icon_url" ) var iconUrl : String? = null)
