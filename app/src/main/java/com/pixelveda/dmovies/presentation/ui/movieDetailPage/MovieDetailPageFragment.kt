package com.pixelveda.dmovies.presentation.ui.movieDetailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pixelveda.dmovies.R
import com.pixelveda.dmovies.databinding.FragmentMovieDetailPageBinding

class MovieDetailPageFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movie = arguments?.let {
            MovieDetailPageFragmentArgs.fromBundle(it).movieObj
        }

        movie?.let {
            binding.text.text = it.title
        }
    }
}