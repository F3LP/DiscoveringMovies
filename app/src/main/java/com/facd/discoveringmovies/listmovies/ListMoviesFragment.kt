package com.facd.discoveringmovies.listmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.facd.discoveringmovies.R
import com.facd.discoveringmovies.databinding.ListMoviesFragmentBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListMoviesFragment : Fragment(), ListMovieAdapter.OnItemClickListener {

    private lateinit var binding: ListMoviesFragmentBinding

    private val viewModel: ListMovieViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.list_movies_fragment,
            container, false
        )

        viewModel.movie.observe(viewLifecycleOwner) { movies ->
            binding.recyclerMovies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = movies?.let { movie -> ListMovieAdapter(movie, this) }
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            binding.progress.isVisible = loading
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onItemClick(id: String, title: String) {
        val bundle = bundleOf("movieId" to id, "title" to title)
        findNavController().navigate(
            R.id.action_list_movies_fragment_to_movieDetailsFragment,
            bundle
        )
    }

    override fun onDestroy() {
        super.onDestroy()
            viewModel.clearListMovies()
    }

}