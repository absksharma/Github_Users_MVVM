package com.example.kt_retrofit.data.models

data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<User>? = null,
    val total_count: Int? = null
)