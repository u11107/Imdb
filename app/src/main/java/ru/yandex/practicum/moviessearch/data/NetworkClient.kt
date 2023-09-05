package ru.yandex.practicum.moviessearch.data

import ru.yandex.practicum.moviessearch.data.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}