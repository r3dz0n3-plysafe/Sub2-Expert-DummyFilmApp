package com.dicoding.dummyfilmapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.dummyfilmapp.R
import com.dicoding.dummyfilmapp.core.data.Resource
import com.dicoding.dummyfilmapp.core.ui.MovieListAdapter
import com.dicoding.dummyfilmapp.core.ui.utils.ViewBindingHolder
import com.dicoding.dummyfilmapp.core.ui.utils.ViewBindingHolderImpl
import com.dicoding.dummyfilmapp.databinding.FragmentMovieBinding
import com.dicoding.dummyfilmapp.ui.DetailFilmActivity
import com.dicoding.dummyfilmapp.viewmodel.FilmViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment(),
    ViewBindingHolder<FragmentMovieBinding> by ViewBindingHolderImpl() {

    private lateinit var movieListAdapter: MovieListAdapter
    private val filmViewModel: FilmViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = initBinding(FragmentMovieBinding.inflate(layoutInflater), this) {

        if (activity != null) {

            movieListAdapter = MovieListAdapter()

            movieListAdapter.onItemClick = { movie ->
                Intent(context, DetailFilmActivity::class.java).also {
                    it.putExtra(DetailFilmActivity.EXTRAS_DATA, movie)
                    it.putExtra(DetailFilmActivity.EXTRAS_SELECTED, "MOVIE")
                    startActivity(it)
                }
            }

            filmViewModel.movie.observe(viewLifecycleOwner, { movie ->
                when (movie) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        showLoading(false)
                        movieListAdapter.setData(movie.data)
                    }
                    is Resource.Error -> {
                        showLoading(false)
                        Toast.makeText(context, getString(R.string.load_error), Toast.LENGTH_LONG)
                            .show()
                    }
                }
            })
            setRecyclerView()
        }
    }

    private fun setRecyclerView() = requireBinding {
        with(rvMovie) {
            layoutManager = LinearLayoutManager(context)
            adapter = movieListAdapter
            setHasFixedSize(true)
        }
    }

    private fun showLoading(state: Boolean = true) = requireBinding {
        apply {
            if (state) pbLoading.visibility =
                View.VISIBLE else pbLoading.visibility = View.GONE
        }
    }
}