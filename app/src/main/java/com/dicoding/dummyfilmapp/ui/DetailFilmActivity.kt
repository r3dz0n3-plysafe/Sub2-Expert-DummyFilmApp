package com.dicoding.dummyfilmapp.ui

import android.content.res.ColorStateList
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.dummyfilmapp.R
import com.dicoding.dummyfilmapp.core.domain.model.Movie
import com.dicoding.dummyfilmapp.core.domain.model.TvShow
import com.dicoding.dummyfilmapp.databinding.ActivityDetailFilmBinding
import com.dicoding.dummyfilmapp.databinding.ContentDetailFilmBinding
import com.dicoding.dummyfilmapp.viewmodel.DetailFilmViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFilmActivity : AppCompatActivity() {

    private lateinit var activityDetailFilmBinding: ActivityDetailFilmBinding
    private lateinit var detailBinding: ContentDetailFilmBinding
    private val detailFilmViewModel: DetailFilmViewModel by viewModel()
    private lateinit var selectedFilm: String

    companion object {
        const val EXTRAS_DATA = "extras_data"
        const val EXTRAS_SELECTED = "extras_selected"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailFilmBinding = ActivityDetailFilmBinding.inflate(layoutInflater)
        detailBinding = activityDetailFilmBinding.detailFilm
        setContentView(activityDetailFilmBinding.root)

        activityDetailFilmBinding.apply {
            setSupportActionBar(toolbarLayout)
            supportActionBar?.title = getString(R.string.title_activity_detail)
            toolbarLayout.setNavigationOnClickListener { onBackPressed() }
        }

        selectedFilm = intent.getStringExtra(EXTRAS_SELECTED).toString()
        when (selectedFilm) {
            "MOVIE" -> {
                val filmDetail = intent.getParcelableExtra<Movie>(EXTRAS_DATA)
                if (filmDetail != null) {
                    getMovieData(filmDetail)
                }
            }
            "TV_SHOW" -> {
                val filmDetail = intent.getParcelableExtra<TvShow>(EXTRAS_DATA)
                if (filmDetail != null) {
                    getTvShowData(filmDetail)
                }
            }
        }
    }

    private fun getMovieData(movieDetail: Movie) {
        detailBinding.apply {
            with(movieDetail) {
                tvDetailTitle.text = title
                tvDetailReleaseDate.text = date
                tvDetailSinopsis.text = sinopsis

                Glide.with(this@DetailFilmActivity)
                    .load("${applicationContext.getString(R.string.base_url_image)}${filmPoster}")
                    .transform(RoundedCorners(20))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh))
                    .error(R.drawable.ic_broken_img)
                    .into(ivDetailPoster)

                favToggle(movieDetail, favoured)
            }
        }
    }

    private fun getTvShowData(tvShowDetail: TvShow) {
        detailBinding.apply {
            with(tvShowDetail) {
                tvDetailTitle.text = title
                tvDetailReleaseDate.text = date
                tvDetailSinopsis.text = sinopsis

                Glide.with(this@DetailFilmActivity)
                    .load("${applicationContext.getString(R.string.base_url_image)}${filmPoster}")
                    .transform(RoundedCorners(20))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh))
                    .error(R.drawable.ic_broken_img)
                    .into(ivDetailPoster)

                favToggle(tvShowDetail, favoured)
            }
        }
    }

    private fun favToggle(entity: Any, state: Boolean) {
        var currentState = state
        favToggleColor(currentState)
        with(activityDetailFilmBinding) {
            fabFav.setOnClickListener {
                currentState = !currentState
                when (selectedFilm) {
                    "MOVIE" -> {
                        detailFilmViewModel.setMovieFavourite(entity as Movie, currentState)
                    }
                    "TV_SHOW" -> {
                        detailFilmViewModel.setTvShowFavourite(entity as TvShow, currentState)
                    }
                }
                if (currentState) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.favoured),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.not_favoured),
                        Toast.LENGTH_LONG
                    ).show()
                }
                favToggleColor(currentState)
            }
        }
    }

    private fun favToggleColor(state: Boolean) {
        with(activityDetailFilmBinding) {
            if (state) {
                fabFav.imageTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this@DetailFilmActivity,
                            R.color.red
                        )
                    )
            } else {
                fabFav.imageTintList =
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            this@DetailFilmActivity,
                            R.color.white
                        )
                    )
            }
        }
    }
}