package com.example.kt_retrofit.data.models


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("items")
    val items: List<User>
)