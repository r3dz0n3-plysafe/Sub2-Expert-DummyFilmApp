package com.dicoding.dummyfilmapp.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val title: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("poster_path")
    val filmPoster: String,
    @SerializedName("overview")
    val sinopsis: String,
)