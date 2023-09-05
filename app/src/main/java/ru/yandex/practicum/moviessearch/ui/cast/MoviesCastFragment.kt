package ru.yandex.practicum.moviessearch.ui.cast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import ru.yandex.practicum.moviessearch.databinding.FragmentMoviesCastBinding
import ru.yandex.practicum.moviessearch.presentation.cast.MoviesCastState
import ru.yandex.practicum.moviessearch.presentation.cast.MoviesCastViewModel

class MoviesCastFragment : Fragment() {

    companion object {

        private const val ARGS_MOVIE_ID = "movie_id"

        fun createArgs(movieId: String): Bundle =
            bundleOf(ARGS_MOVIE_ID to movieId)
    }

    private val moviesCastViewModel: MoviesCastViewModel by viewModel {
        // параметр movieId берём из аргументов фрагмента, а не Intent
        parametersOf(requireArguments().getString(ARGS_MOVIE_ID))
    }

    private val adapter = ListDelegationAdapter(
        movieCastHeaderDelegate(),
        movieCastPersonDelegate(),
    )

    private lateinit var binding: FragmentMoviesCastBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMoviesCastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.moviesCastRecyclerView.adapter = adapter
        binding.moviesCastRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        moviesCastViewModel.observeState().observe(viewLifecycleOwner) {
            when (it) {
                is MoviesCastState.Content -> showContent(it)
                is MoviesCastState.Error -> showError(it)
                is MoviesCastState.Loading -> showLoading()
            }
        }
    }

    private fun showLoading() {
        binding.contentContainer.isVisible = false
        binding.errorMessageTextView.isVisible = false

        binding.progressBar.isVisible = true
    }

    private fun showError(state: MoviesCastState.Error) {
        binding.contentContainer.isVisible = false
        binding.progressBar.isVisible = false

        binding.errorMessageTextView.isVisible = true
        binding.errorMessageTextView.text = state.message
    }

    private fun showContent(state: MoviesCastState.Content) {
        binding.progressBar.isVisible = false
        binding.errorMessageTextView.isVisible = false

        binding.contentContainer.isVisible = true
        binding.movieTitle.text = state.fullTitle
        adapter.items = state.items
        adapter.notifyDataSetChanged()
    }

}
