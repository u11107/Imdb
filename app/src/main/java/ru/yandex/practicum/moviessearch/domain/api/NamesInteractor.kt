package ru.yandex.practicum.moviessearch.domain.api

import kotlinx.coroutines.flow.Flow
import ru.yandex.practicum.moviessearch.domain.models.Person

interface NamesInteractor {

    fun searchNames(expression: String): Flow<Pair<List<Person>?, String?>>
    
}