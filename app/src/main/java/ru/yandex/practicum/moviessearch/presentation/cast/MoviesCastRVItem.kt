package ru.yandex.practicum.moviessearch.presentation.cast

import ru.yandex.practicum.moviessearch.core.ui.RVItem
import ru.yandex.practicum.moviessearch.domain.models.MovieCastPerson


sealed interface MoviesCastRVItem : RVItem {

    data class HeaderItem(
        val headerText: String,
    ) : MoviesCastRVItem

    data class PersonItem(
        val data: MovieCastPerson,
    ) : MoviesCastRVItem

}
