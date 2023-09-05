package ru.yandex.practicum.moviessearch

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import ru.yandex.practicum.moviessearch.di.dataModule
import ru.yandex.practicum.moviessearch.di.interactorModule
import ru.yandex.practicum.moviessearch.di.navigationModule
import ru.yandex.practicum.moviessearch.di.repositoryModule
import ru.yandex.practicum.moviessearch.di.viewModelModule

class MoviesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@MoviesApplication)
            modules(
                dataModule,
                repositoryModule,
                interactorModule,
                viewModelModule,
                navigationModule
            )
        }
    }
}
