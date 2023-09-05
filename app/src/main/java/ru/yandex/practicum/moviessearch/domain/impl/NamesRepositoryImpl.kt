package ru.yandex.practicum.moviessearch.domain.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.yandex.practicum.moviessearch.data.NetworkClient
import ru.yandex.practicum.moviessearch.data.dto.NamesSearchRequest
import ru.yandex.practicum.moviessearch.data.dto.NamesSearchResponse
import ru.yandex.practicum.moviessearch.domain.api.NamesRepository
import ru.yandex.practicum.moviessearch.domain.models.Person
import ru.yandex.practicum.moviessearch.util.Resource

class NamesRepositoryImpl(private val networkClient: NetworkClient) : NamesRepository {

    override fun searchNames(expression: String): Flow<Resource<List<Person>>> = flow {
        val response = networkClient.doRequestSuspend(NamesSearchRequest(expression))
         when (response.resultCode) {
            -1 -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }
             200 -> {
                 with(response as NamesSearchResponse) {
                     val data = results.map {
                         Person(id = it.id,
                             name = it.title,
                             description = it.description,
                             photoUrl = it.image)
                     }
                     emit(Resource.Success(data))
                 }
             }
            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}