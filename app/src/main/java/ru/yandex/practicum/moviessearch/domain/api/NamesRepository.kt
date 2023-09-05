package ru.yandex.practicum.moviessearch.domain.api

import ru.yandex.practicum.moviessearch.domain.models.Person
import ru.yandex.practicum.moviessearch.util.Resource

interface NamesRepository {

    fun searchNames(expression: String): Resource<List<Person>>
}