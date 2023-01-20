package com.example.p6_flows.ui.fragment.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.p6_flows.R
import com.example.p6_flows.databinding.CardViewMovieListBinding
import com.example.p6_flows.domain.model.Movie

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
            val arrow = binding.buttonExpand
            val hiddenView = binding.hiddenView
            arrow.setOnClickListener(View.OnClickListener {
                if (hiddenView.visibility == View.GONE) {
                    hiddenView.visibility = View.VISIBLE
                    buttonExpand.text = "Show more"
                } else {
                    hiddenView.visibility = View.GONE
                    buttonExpand.text = "Show less"
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