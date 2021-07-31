package com.dicoding.dummyfilmapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val id: Int,

    val title: String,

    val date: String,

    val filmPoster: String,

    val sinopsis: String,

    var favoured: Boolean = false
) : Parcelable