package com.example.moviedbapp.models


import com.google.gson.annotations.SerializedName

data class GetTvPopularsResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val tvItems: List<TvItem>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)