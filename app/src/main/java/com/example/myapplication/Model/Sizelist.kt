package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Sizelist(
    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("size" ) var size : String? = null,
    @SerializedName("select" ) var select : Int? = 0

): Serializable
