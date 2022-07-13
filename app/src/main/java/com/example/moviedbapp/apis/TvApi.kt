package com.example.moviedbapp.apis

import com.example.moviedbapp.SerialDetailsFragment
import com.example.moviedbapp.models.GetTvPopularsResponse
import com.example.moviedbapp.models.SerialDetail
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvApi {
    @GET("tv/top_rated")
    suspend fun getPopularTv(
        @Query("api_key")
        apikey: String):GetTvPopularsResponse
    @GET("tv/{id}")
    suspend fun getSerialDetail(
        @Path ("id")
        id: Int,
        @Query("api_key")
        apikey: String): SerialDetail
}