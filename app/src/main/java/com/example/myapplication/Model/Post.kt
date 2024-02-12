package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Post(
    @SerializedName("id"           ) var id           : Int?                = null,
    @SerializedName("icon_url"     ) var iconUrl      : ArrayList<IconUrl>  = arrayListOf(),
    @SerializedName("type"         ) var type         : String?             = null,
    @SerializedName("name"         ) var name         : String?             = null,
    @SerializedName("price"        ) var price        : String?             = null,
    @SerializedName("country"      ) var country      : String?             = null,
    @SerializedName("sizelist"     ) var sizelist     : ArrayList<Sizelist> = arrayListOf(),
    @SerializedName("desc"         ) var desc         : String?             = null,
    @SerializedName("freedelivery" ) var freedelivery : String?             = null,
    @SerializedName("like"           ) var like           : Int?                = 0,

    ) : Serializable
