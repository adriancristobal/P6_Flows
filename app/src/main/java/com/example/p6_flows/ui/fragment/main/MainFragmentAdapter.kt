package com.example.p6_flows.ui.fragment.main

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
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
                .load("http://image.tmdb.org/t/p/w500"+m.poster_path)
                .into(imageMovie)




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

            titleMovie.text = m.title
            overviewMovie.text = m.overview


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