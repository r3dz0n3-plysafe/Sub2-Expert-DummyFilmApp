package com.dicoding.dummyfilmapp.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val filmPoster: String,
    @SerializedName("overview")
    val sinopsis: String
)