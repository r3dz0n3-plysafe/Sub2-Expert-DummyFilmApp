package com.dicoding.dummyfilmapp.core.data.source.remote.response.tvshow

import com.google.gson.annotations.SerializedName

data class TvShowListResponse(
    @SerializedName("results")
    val results: List<TvShowResponse>
)