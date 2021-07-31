package com.dicoding.dummyfilmapp.core.utils

import com.dicoding.dummyfilmapp.core.data.source.local.entity.MovieEntity
import com.dicoding.dummyfilmapp.core.data.source.local.entity.TvShowEntity
import com.dicoding.dummyfilmapp.core.data.source.remote.response.movie.MovieResponse
import com.dicoding.dummyfilmapp.core.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.dummyfilmapp.core.domain.model.Movie
import com.dicoding.dummyfilmapp.core.domain.model.TvShow

object DataMapper {
    fun mapMovieResponseToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movieList = ArrayList<MovieEntity>()
        input.map {
            val movie = MovieEntity(
                id = it.id,
                title = it.title,
                date = it.date,
                filmPoster = it.filmPoster,
                sinopsis = it.sinopsis,
                favoured = false
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                id = it.id,
                title = it.title,
                date = it.date,
                filmPoster = it.filmPoster,
                sinopsis = it.sinopsis,
                favoured = it.favoured
            )
        }

    fun mapMovieDomainToEntity(input: Movie) = MovieEntity(
        id = input.id,
        title = input.title,
        date = input.date,
        filmPoster = input.filmPoster,
        sinopsis = input.sinopsis,
        favoured = input.favoured
    )

    fun mapTvShowResponseToEntities(input: List<TvShowResponse>): List<TvShowEntity> {
        val tvShowList = ArrayList<TvShowEntity>()
        input.map {
            val tvShow = TvShowEntity(
                id = it.id,
                title = it.title,
                date = it.date,
                filmPoster = it.filmPoster,
                sinopsis = it.sinopsis,
                favoured = false
            )
            tvShowList.add(tvShow)
        }
        return tvShowList
    }

    fun mapTvShowEntitiesToDomain(input: List<TvShowEntity>): List<TvShow> =
        input.map {
            TvShow(
                id = it.id,
                title = it.title,
                date = it.date,
                filmPoster = it.filmPoster,
                sinopsis = it.sinopsis,
                favoured = it.favoured
            )
        }

    fun mapTvShowDomainToEntity(input: TvShow) = TvShowEntity(
        id = input.id,
        title = input.title,
        date = input.date,
        filmPoster = input.filmPoster,
        sinopsis = input.sinopsis,
        favoured = input.favoured
    )
}