package ru.yandex.practicum.moviessearch.domain.api

import ru.yandex.practicum.moviessearch.domain.models.Person

interface NamesInteractor {

    fun searchNames(expression: String, consumer: NamesConsumer)

    interface NamesConsumer {
        fun consume(foundNames: List<Person>?, errorMessage: String?)
    }
}