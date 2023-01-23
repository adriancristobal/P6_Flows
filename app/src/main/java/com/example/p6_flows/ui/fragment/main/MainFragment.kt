package com.example.p6_flows.ui.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.p6_flows.R
import com.example.p6_flows.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var movieAdapter: MainFragmentAdapter

    private val viewModel: MainFragmentViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        changeStatus()
    }

    override fun onStart() {
        super.onStart()
        showList()
    }


    private fun init() {

        val layoutManager = LinearLayoutManager(context)
        binding.rvMoviesList.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(
            binding.rvMoviesList.context,
            layoutManager.orientation
        )
        binding.rvMoviesList.addItemDecoration(dividerItemDecoration)

        movieAdapter = MainFragmentAdapter()
        binding.rvMoviesList.adapter = movieAdapter
    }



    private fun changeStatus() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
//                    when (value) {
//                        is UiState.Failure -> {
//                            Toast.makeText(this@MainActivity, value.mensaje, Toast.LENGTH_SHORT)
//                                .show()
//                            binding.loading.visibility = View.GONE
//                        }
                    binding.loading.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                    movieAdapter.submitList(state.movies)
                    state.error.let {
                        //Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        viewModel.handleEvent(MainContract.Event.ShowMessage)
                    }
//                    }
                }
            }
        }
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiError.collect {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun showList() {
        viewModel.handleEvent(
            MainContract.Event.GetData
        )
    }







    





}