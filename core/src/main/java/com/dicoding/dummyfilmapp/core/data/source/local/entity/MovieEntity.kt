package com.dicoding.dummyfilmapp.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_entity")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "poster")
    val filmPoster: String,

    @ColumnInfo(name = "sinopsis")
    val sinopsis: String,

    @ColumnInfo(name = "favoured")
    var favoured: Boolean = false
)