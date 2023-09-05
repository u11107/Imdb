package ru.yandex.practicum.moviessearch.domain.impl

import ru.yandex.practicum.moviessearch.data.NetworkClient
import ru.yandex.practicum.moviessearch.data.dto.NamesSearchRequest
import ru.yandex.practicum.moviessearch.data.dto.NamesSearchResponse
import ru.yandex.practicum.moviessearch.domain.api.NamesRepository
import ru.yandex.practicum.moviessearch.domain.models.Person
import ru.yandex.practicum.moviessearch.util.Resource

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Resource<List<Person>> {
        val response = networkClient.doRequest(NamesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as NamesSearchResponse) {
                    Resource.Success(results.map {
                        Person(id = it.id,
                            name = it.title,
                            description = it.description,
                            photoUrl = it.image)
                    })
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }
}