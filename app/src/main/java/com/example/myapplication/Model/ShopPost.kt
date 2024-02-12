package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ShopPost(
    @SerializedName("id"           ) var id           : Int?                = null,
    @SerializedName("name"         ) var name         : String?             = null,
    @SerializedName("price"        ) var price        : String?             = null,
    @SerializedName("quantity"     ) var quantity     : Int?    = 0,
    ) : Serializable
