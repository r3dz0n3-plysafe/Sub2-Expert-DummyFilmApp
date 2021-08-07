package com.dicoding.dummyfilmapp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.dicoding.dummyfilmapp.R
import com.dicoding.dummyfilmapp.core.data.Resource
import com.dicoding.dummyfilmapp.core.ui.TvShowListAdapter
import com.dicoding.dummyfilmapp.core.ui.utils.ViewBindingHolder
import com.dicoding.dummyfilmapp.core.ui.utils.ViewBindingHolderImpl
import com.dicoding.dummyfilmapp.databinding.FragmentTvShowBinding
import com.dicoding.dummyfilmapp.ui.DetailFilmActivity
import com.dicoding.dummyfilmapp.viewmodel.FilmViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(),
    ViewBindingHolder<FragmentTvShowBinding> by ViewBindingHolderImpl() {

    private lateinit var tvShowListAdapter: TvShowListAdapter
    private val filmViewModel: FilmViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View = initBinding(FragmentTvShowBinding.inflate(layoutInflater), this) {
        if (activity != null) {

            tvShowListAdapter = TvShowListAdapter()

            tvShowListAdapter.onItemClick = { tvShow ->
                Intent(context, DetailFilmActivity::class.java).also {
                    it.putExtra(DetailFilmActivity.EXTRAS_DATA, tvShow)
                    it.putExtra(DetailFilmActivity.EXTRAS_SELECTED, "TV_SHOW")
                    startActivity(it)
                }
            }

            filmViewModel.tvShow.observe(viewLifecycleOwner, { tvShow ->
                when (tvShow) {
                    is Resource.Loading -> showLoading()
                    is Resource.Success -> {
                        showLoading(false)
                        tvShowListAdapter.setData(tvShow.data)
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
        with(rvTv) {
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)
            adapter = tvShowListAdapter
        }
    }

    private fun showLoading(state: Boolean = true) = requireBinding {
        apply {
            if (state) pbLoading.visibility =
                View.VISIBLE else pbLoading.visibility = View.GONE
        }
    }
}