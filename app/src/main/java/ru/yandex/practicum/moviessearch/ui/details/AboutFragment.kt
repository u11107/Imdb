package ru.yandex.practicum.moviessearch.ui.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.yandex.practicum.moviessearch.R
import ru.yandex.practicum.moviessearch.core.navigation.Router
import ru.yandex.practicum.moviessearch.databinding.FragmentAboutBinding
import ru.yandex.practicum.moviessearch.domain.models.MovieDetails
import ru.yandex.practicum.moviessearch.presentation.details.AboutState
import ru.yandex.practicum.moviessearch.presentation.details.AboutViewModel
import ru.yandex.practicum.moviessearch.ui.cast.MoviesCastFragment

class AboutFragment : Fragment() {

    companion object {
        private const val MOVIE_ID = "movie_id"

        fun newInstance(movieId: String) = AboutFragment().apply {
            arguments = Bundle().apply {
                putString(MOVIE_ID, movieId)
            }
        }
    }

    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.showCastButton.setOnClickListener {
            findNavController().navigate(R.id.action_detailsFragment_to_moviesCastFragment,
                MoviesCastFragment.createArgs(requireArguments().getString(MOVIE_ID).orEmpty()))
        }
    }

    private fun showErrorMessage(message: String) {
        Log.d("TEST_", "showErrorMessage: $message")
        binding.apply {
            details.visibility = View.GONE
            errorMessage.visibility = View.VISIBLE
            errorMessage.text = message
        }
    }

    private fun showDetails(movieDetails: MovieDetails) {
        binding.apply {
            details.visibility = View.VISIBLE
            errorMessage.visibility = View.GONE
            title.text = movieDetails.title
            ratingValue.text = movieDetails.imDbRating
            yearValue.text = movieDetails.year
            countryValue.text = movieDetails.countries
            genreValue.text = movieDetails.genres
            directorValue.text = movieDetails.directors
            writerValue.text = movieDetails.writers
            castValue.text = movieDetails.stars
            plot.text = movieDetails.plot
        }

    }
}
