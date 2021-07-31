package com.dicoding.dummyfilmapp.core.data

import com.dicoding.dummyfilmapp.core.data.source.local.LocalDataSource
import com.dicoding.dummyfilmapp.core.data.source.remote.RemoteDataSource
import com.dicoding.dummyfilmapp.core.data.source.remote.network.ApiResponse
import com.dicoding.dummyfilmapp.core.data.source.remote.response.movie.MovieResponse
import com.dicoding.dummyfilmapp.core.data.source.remote.response.tvshow.TvShowResponse
import com.dicoding.dummyfilmapp.core.domain.model.Movie
import com.dicoding.dummyfilmapp.core.domain.model.TvShow
import com.dicoding.dummyfilmapp.core.domain.repository.IFilmRepository
import com.dicoding.dummyfilmapp.core.utils.AppExecutors
import com.dicoding.dummyfilmapp.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FilmRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors,
) : IFilmRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object :
            NetworkBoundResource<List<Movie>, List<MovieResponse>>() {

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovieList()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponseToEntities(data)
                localDataSource.insertMovie(movieList)
            }
        }.asFlow()

    override fun getAllFavMovie(): Flow<List<Movie>> {
        return localDataSource.getAllFavMovie().map { DataMapper.mapMovieEntitiesToDomain(it) }
    }


    override fun setMovieFav(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute {
            localDataSource.updateFavMovie(movieEntity, state)
        }
    }

    override fun getAllTvShow(): Flow<Resource<List<TvShow>>> =
        object :
            NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getAllTvShow().map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShowList()

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponseToEntities(data)
                localDataSource.insertTvShow(tvShowList)
            }
        }.asFlow()

    override fun getAllFavTvShow(): Flow<List<TvShow>> {
        return localDataSource.getAllFavTvShow().map { DataMapper.mapTvShowEntitiesToDomain(it) }
    }

    override fun setTvShowFav(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute {
            localDataSource.updateFavTvShow(tvShowEntity, state)
        }
    }
}