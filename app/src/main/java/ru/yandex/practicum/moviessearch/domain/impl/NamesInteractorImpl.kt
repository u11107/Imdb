package ru.yandex.practicum.moviessearch.domain.impl

import ru.yandex.practicum.moviessearch.domain.api.NamesInteractor
import ru.yandex.practicum.moviessearch.domain.api.NamesRepository
import ru.yandex.practicum.moviessearch.util.Resource
import java.util.concurrent.Executors

class NamesInteracrorImpl(private val repository: NamesRepository) : NamesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchNames(expression: String, consumer: NamesInteractor.NamesConsumer) {
        executor.execute {
            when(val resource = repository.searchNames(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }
}