package ru.yandex.practicum.moviessearch.di

import org.koin.dsl.module
import ru.yandex.practicum.moviessearch.domain.api.MoviesInteractor
import ru.yandex.practicum.moviessearch.domain.api.NamesInteractor
import ru.yandex.practicum.moviessearch.domain.impl.MoviesInteractorImpl
import ru.yandex.practicum.moviessearch.domain.impl.NamesInteracrorImpl

val interactorModule = module {

    single<MoviesInteractor> {
        MoviesInteractorImpl(get())
    }

    single<NamesInteractor> {
        NamesInteracrorImpl(get())
    }

}