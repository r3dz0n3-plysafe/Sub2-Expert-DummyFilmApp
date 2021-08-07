package com.dicoding.dummyfilmapp.core.data.source.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)