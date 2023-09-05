package ru.yandex.practicum.moviessearch.presentation.movies

import ru.yandex.practicum.moviessearch.domain.models.Movie

sealed interface MoviesState {

    object Loading : MoviesState

    data class Content(
            val movies: List<Movie>
    ) : MoviesState

    data class Error(
            val message: String
    ) : MoviesState

    data class Empty(
            val message: String
    ) : MoviesState

}