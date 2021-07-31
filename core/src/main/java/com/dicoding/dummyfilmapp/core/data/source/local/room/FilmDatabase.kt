package com.dicoding.dummyfilmapp.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dicoding.dummyfilmapp.core.data.source.local.entity.MovieEntity
import com.dicoding.dummyfilmapp.core.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}