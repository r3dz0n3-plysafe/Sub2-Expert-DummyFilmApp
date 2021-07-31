package com.dicoding.dummyfilmapp.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    val id: Int,
    val title: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("poster_path")
    val filmPoster: String,
    @SerializedName("overview")
    val sinopsis: String
)