package com.pixelveda.dmovies.presentation.ui.movieDetailPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.pixelveda.dmovies.R
import com.pixelveda.dmovies.common.Utils
import com.pixelveda.dmovies.databinding.FragmentMovieDetailPageBinding
import com.squareup.picasso.Picasso

class MovieDetailPageFragment : Fragment() {
    lateinit var binding: FragmentMovieDetailPageBinding
    val viewModel by viewModels<MovieDetailViewModel>()

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
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val movie = arguments?.let {
            MovieDetailPageFragmentArgs.fromBundle(it).movieObj
        }

        movie?.let {
            binding.text.text = it.title
            Picasso.get()
                .load(it.poster)
                .error(R.drawable.ic_launcher_background)
                .into(binding.ivPoster)

            binding.tvReleaseData.text = it.country
            binding.tvRunTime.text = it.imdbRating
            binding.tvGenre.text = it.genre
        }

        binding.ivBookMark.setOnClickListener {
            movie?.let {
                Utils.defaultDialog(requireContext(),"Movie Saved","Movie Saved Successfully")
                viewModel.saveMovieToDb(it)
            }
        }
    }
}