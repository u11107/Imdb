package ru.yandex.practicum.moviessearch.presentation.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.yandex.practicum.moviessearch.domain.api.MoviesInteractor
import ru.yandex.practicum.moviessearch.domain.models.MovieDetails

class AboutViewModel(private val movieId: String,
                     private val moviesInteractor: MoviesInteractor, ) : ViewModel() {


    private val stateLiveData = MutableLiveData<AboutState>()
    fun observeState(): LiveData<AboutState> = stateLiveData

    init {
        moviesInteractor.getMoviesDetails(movieId, object : MoviesInteractor.MovieDetailsConsumer {

            override fun consume(movieDetails: MovieDetails?, errorMessage: String?) {
                if (movieDetails != null) {
                    stateLiveData.postValue(AboutState.Content(movieDetails))
                } else {
                    stateLiveData.postValue(AboutState.Error(errorMessage ?: "Unknown error"))
                }
            }
        })
    }
}