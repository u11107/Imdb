package ru.yandex.practicum.moviessearch.presentation.names

import ru.yandex.practicum.moviessearch.domain.models.Person

sealed interface NamesState {

    object Loading : NamesState

    data class Content(
        val persons: List<Person>
    ) : NamesState

    data class Error(
        val message: String
    ) : NamesState

    data class Empty(
        val message: String
    ) : NamesState

}