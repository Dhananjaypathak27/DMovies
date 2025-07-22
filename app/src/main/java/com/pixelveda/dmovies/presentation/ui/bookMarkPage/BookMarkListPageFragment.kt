package com.pixelveda.dmovies.presentation.ui.bookMarkPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelveda.dmovies.databinding.FragmentBookMarkListPageBinding
import com.pixelveda.dmovies.domain.model.Movie
import kotlinx.coroutines.launch

class BookMarkListPageFragment : Fragment(),BookMarkListListener,BookMarkListDeleteListener {
    lateinit var binding: FragmentBookMarkListPageBinding
    private val viewModel by viewModels<BookMarkListViewModel>()
     var list = mutableListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBookMarkListPageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = this.viewModel

        lifecycleScope.launch {
            viewModel.moviesListState.collect { state ->
               if(state.isLoading){
                 //Show progress bar
                }
                if(state.error.isNotBlank()){
                    //show error
                }
                if(state.movies.isNotEmpty()){
                    list.clear()
                    list.addAll(state.movies)
                    binding.recyclerView.adapter?.notifyDataSetChanged()
                }

            }
        }

        binding.recyclerView.apply {
            adapter = BookMarkAdapter(list,this@BookMarkListPageFragment,this@BookMarkListPageFragment)
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())

        }

    }

    override fun onItemClick(pos: Int) {
        val action = BookMarkListPageFragmentDirections.actionBookMarkListPageFragmentToMovieDetailPageFragment(
            list[pos]
        )
        findNavController().navigate(action)
    }

    override fun onDeleteIconClick(pos: Int) {
        TODO("Not yet implemented")
    }
}