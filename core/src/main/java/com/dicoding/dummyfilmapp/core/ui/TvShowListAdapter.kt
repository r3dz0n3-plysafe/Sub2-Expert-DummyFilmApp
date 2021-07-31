package com.dicoding.dummyfilmapp.core.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.dummyfilmapp.core.R
import com.dicoding.dummyfilmapp.core.databinding.ItemListTvshowBinding
import com.dicoding.dummyfilmapp.core.domain.model.TvShow

class TvShowListAdapter :
    RecyclerView.Adapter<TvShowListAdapter.TvShowListViewHolder>() {

    private var listTvShow = ArrayList<TvShow>()
    var onItemClick: ((TvShow) -> Unit)? = null

    fun setData(newListData: List<TvShow>?) {
        if (newListData == null) return
        listTvShow.clear()
        listTvShow.addAll(newListData)
        notifyDataSetChanged()
    }

    inner class TvShowListViewHolder(private val binding: ItemListTvshowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            binding.apply {
                val tempTest =
                    with(tvShow) { "$id\n$title\n$date\n${itemView.context.getString(R.string.base_url_image)}$filmPoster\n$sinopsis" }
                Log.d("resultTest", "Result :\n${tempTest}")
                Glide.with(itemView.context)
                    .load("${itemView.context.getString(R.string.base_url_image)}${tvShow.filmPoster}")
                    .transform(RoundedCorners(20))
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_refresh))
                    .error(R.drawable.ic_broken_img)
                    .into(ivPosterTvshow)

                tvFilmTitleTvshow.text = tvShow.title
                tvReleaseDateTvshow.text = tvShow.date

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listTvShow[absoluteAdapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowListViewHolder {
        val view = ItemListTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowListViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowListViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size
}