package com.example.myapplication.Model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class IconUrl(  @SerializedName("icon" ) var icon : String? = null
):Serializable
