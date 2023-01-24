package com.example.p6_flows.ui.fragment.main

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.p6_flows.R
import com.example.p6_flows.databinding.CardViewMovieListBinding
import com.example.p6_flows.domain.model.Movie
import java.net.URL


class MainFragmentAdapter : ListAdapter<Movie, MainFragmentAdapter.ItemViewholder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewholder {
        return ItemViewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view_movie_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewholder, position: Int) = with(holder) {
        val item = getItem(position)
        bind(item)
    }

    inner class ItemViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val binding = CardViewMovieListBinding.bind(itemView)


        fun bind(m: Movie) = with(binding) {

            titleMovie.text = m.title
            overviewMovie.text = m.overview

            Glide.with(itemView.context)
                .load("http://image.tmdb.org/t/p/w342"+m.poster_path)
                .into(imageMovie)


/*
            coil.ImageLoader(itemView.context).load("http://image.tmdb.org/t/p/w342"+m.poster_path) {
                crossfade(true)
                placeholder(R.drawable.ic_launcher_foreground)
                error(R.drawable.ic_launcher_foreground)
            }

            imageMovie.load("http://image.tmdb.org/t/p/w342"+m.poster_path) {
                crossfade(true)
                crossfade(1000)
            }

 */




            val arrow = binding.buttonExpand
            val hiddenView = binding.hiddenView
            arrow.setOnClickListener(View.OnClickListener {
                if (hiddenView.visibility == View.GONE) {
                    hiddenView.visibility = View.VISIBLE
                    buttonExpand.text = "Show less"
                } else {
                    hiddenView.visibility = View.GONE
                    buttonExpand.text = "Show more"
                }
            })


        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.title == newItem.title && oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }
}