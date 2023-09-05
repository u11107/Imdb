package ru.yandex.practicum.moviessearch.di

import org.koin.dsl.module
import ru.yandex.practicum.moviessearch.core.navigation.Router
import ru.yandex.practicum.moviessearch.core.navigation.RouterImpl


val navigationModule = module {
    val router = RouterImpl()

    single<Router> { router }
    single { router.navigatorHolder }
}
