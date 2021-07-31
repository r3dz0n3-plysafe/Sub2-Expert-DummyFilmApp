package com.dicoding.dummyfilmapp.core.data.source.local.room

import androidx.room.*
import com.dicoding.dummyfilmapp.core.data.source.local.entity.MovieEntity
import com.dicoding.dummyfilmapp.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM movie_entity")
    fun getAllMovie(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie_entity WHERE favoured = 1")
    fun getAllFavMovie(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Query("SELECT * FROM tv_show_entity")
    fun getAllTvShow(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_show_entity WHERE favoured = 1")
    fun getAllFavTvShow(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}