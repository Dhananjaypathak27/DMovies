package com.pixelveda.dmovies.presentation.ui.searchPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.pixelveda.dmovies.databinding.FragmentSearchMovieBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SearchMovieFragment : Fragment() {

    private lateinit var binding: FragmentSearchMovieBinding
    private val viewModel: SearchMovieViewModel by viewModels<SearchMovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchMovieBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        lifecycleScope.launch {
            viewModel.movieState.collectLatest { state ->
                if (state.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                }
                if (state.error.isNotBlank()) {
                    binding.progressBar.visibility = View.GONE
                }
                state.movie?.let {
                    binding.progressBar.visibility = View.GONE
                    if (it.response == "True") {
                        binding.text.text = it.title
                    } else {
                        binding.text.text = it.error
                    }
                }
            }
        }

        binding.text.setOnClickListener {
            val action =
                SearchMovieFragmentDirections.actionSearchMovieFragmentToMovieDetailPageFragment(
                    viewModel.movieState.value.movie!!
                )
            findNavController().navigate(action)
        }

        binding.ivBookMark.setOnClickListener {
            val action =
                SearchMovieFragmentDirections.actionSearchMovieFragmentToBookMarkListPageFragment()
            findNavController().navigate(action)
        }

    }

}