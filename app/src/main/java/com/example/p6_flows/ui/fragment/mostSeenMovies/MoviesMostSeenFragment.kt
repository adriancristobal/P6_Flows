package com.example.p6_flows.ui.fragment.mostSeenMovies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.p6_flows.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesMostSeenFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies_most_seen, container, false)
    }

}