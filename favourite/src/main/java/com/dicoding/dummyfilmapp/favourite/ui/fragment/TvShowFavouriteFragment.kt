package com.dicoding.dummyfilmapp.favourite.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.dummyfilmapp.core.ui.TvShowListAdapter
import com.dicoding.dummyfilmapp.core.ui.utils.ViewBindingHolder
import com.dicoding.dummyfilmapp.core.ui.utils.ViewBindingHolderImpl
import com.dicoding.dummyfilmapp.favourite.databinding.FragmentTvShowFavouriteBinding
import com.dicoding.dummyfilmapp.favourite.ui.FavouriteViewModel
import com.dicoding.dummyfilmapp.ui.DetailFilmActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFavouriteFragment : Fragment(),
    ViewBindingHolder<FragmentTvShowFavouriteBinding> by ViewBindingHolderImpl() {

    private lateinit var tvShowFavListAdapter: TvShowListAdapter
    private val favouriteViewModel: FavouriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = initBinding(FragmentTvShowFavouriteBinding.inflate(layoutInflater), this) {
        if (activity != null) {
            showLoading()
            tvShowFavListAdapter = TvShowListAdapter()
            tvShowFavListAdapter.onItemClick = { movie ->
                Intent(context, DetailFilmActivity::class.java).also {
                    it.putExtra(DetailFilmActivity.EXTRAS_DATA, movie)
                    it.putExtra(DetailFilmActivity.EXTRAS_SELECTED, "TV_SHOW")
                    startActivity(it)
                }
            }

            favouriteViewModel.tvShowFav.observe(viewLifecycleOwner, {
                showLoading(false)
                tvShowFavListAdapter.setData(it)
                tvEmpty.visibility =
                    if (it.isNotEmpty()) View.GONE else View.VISIBLE
            })

            setRecyclerView()

        }
    }

    private fun setRecyclerView() = requireBinding {
        with(rvTvFav) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowFavListAdapter
        }
    }

    private fun showLoading(state: Boolean = true) = requireBinding {
        apply {
            if (state) pbLoading.visibility =
                View.VISIBLE else pbLoading.visibility = View.GONE
        }
    }
}