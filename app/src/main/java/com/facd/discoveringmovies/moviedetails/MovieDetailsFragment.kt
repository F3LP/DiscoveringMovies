package com.facd.discoveringmovies.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.facd.discoveringmovies.R
import com.facd.discoveringmovies.databinding.MovieDetailsFragmentBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieDetailsFragment : Fragment() {

    private lateinit var binding: MovieDetailsFragmentBinding
    private val viewModel: MovieDetailsViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =
            DataBindingUtil.inflate(inflater, R.layout.movie_details_fragment, container, false)

        val movieId = arguments?.getString("movieId")
        val movieTitle = arguments?.getString("title")

        activity?.toolbar?.title = movieTitle


        if (movieId != null) {
            viewModel.searchMovieById(movieId)
        }

        viewModel.movie.observe(viewLifecycleOwner) { movie ->
            Glide.with(this).load(movie?.image).into(binding.imageMovieCover)
            binding.tvTitle.text = movie?.title
            binding.tvYear.text = movie?.year
            binding.tvImdbRating.text = movie?.imDbRating
            binding.imdbRatingVotes.text = movie?.imDbRatingVotes
            binding.tvDirectorName.text =
                if (movie?.directors != "") movie?.directors else getString(R.string.not_available)
            binding.tvStarsName.text =
                if (movie?.stars != "") movie?.stars else getString(R.string.not_available)
            binding.tvGenreName.text =
                if (movie?.genres != "") movie?.genres else getString(R.string.not_available)
            binding.tvRunTimeMins.text =
                if (movie?.runtimeMins != "") movie?.runtimeMins.plus(" ${getString(R.string.minutes)}") else getString(
                    R.string.not_available
                )
            binding.tvPlot.text =
                if (movie?.plot != "") movie?.plot else getString(R.string.not_available)
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

    override fun onDestroy() {
        super.onDestroy()
        viewModel.clearMovieDetails()
    }
}