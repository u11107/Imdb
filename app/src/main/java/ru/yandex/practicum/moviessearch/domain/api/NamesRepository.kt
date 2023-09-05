package ru.yandex.practicum.moviessearch.domain.api

import kotlinx.coroutines.flow.Flow
import ru.yandex.practicum.moviessearch.domain.models.Person
import ru.yandex.practicum.moviessearch.util.Resource

interface NamesRepository {

    fun searchNames(expression: String): Flow<Resource<List<Person>>>
}