package com.dicoding.dummyfilmapp.favourite.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.dummyfilmapp.core.ui.MovieListAdapter
import com.dicoding.dummyfilmapp.favourite.databinding.FragmentMovieFavouriteBinding
import com.dicoding.dummyfilmapp.favourite.ui.FavouriteViewModel
import com.dicoding.dummyfilmapp.ui.DetailFilmActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFavouriteFragment : Fragment() {

    private lateinit var favouriteBinding: FragmentMovieFavouriteBinding
    private lateinit var movieFavListAdapter: MovieListAdapter
    private val favouriteViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        favouriteBinding =
            FragmentMovieFavouriteBinding.inflate(layoutInflater, container, false)
        return favouriteBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            showLoading()
            movieFavListAdapter = MovieListAdapter()
            movieFavListAdapter.onItemClick = { movie ->
                Intent(context, DetailFilmActivity::class.java).also {
                    it.putExtra(DetailFilmActivity.EXTRAS_DATA, movie)
                    it.putExtra(DetailFilmActivity.EXTRAS_SELECTED, "MOVIE")
                    startActivity(it)
                }
            }

            favouriteViewModel.movieFav.observe(viewLifecycleOwner, {
                showLoading(false)
                movieFavListAdapter.setData(it)
                favouriteBinding.tvEmpty.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            })

            favouriteBinding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieFavListAdapter
            }
        }
    }

    private fun showLoading(state: Boolean = true) {
        favouriteBinding.apply {
            if (state) pbLoading.visibility =
                View.VISIBLE else pbLoading.visibility = View.GONE
        }
    }
}