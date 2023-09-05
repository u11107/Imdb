package ru.yandex.practicum.moviessearch.domain.impl

import ru.yandex.practicum.moviessearch.domain.api.MoviesInteractor
import ru.yandex.practicum.moviessearch.domain.api.MoviesRepository
import ru.yandex.practicum.moviessearch.util.Resource
import java.util.concurrent.Executors

class MoviesInteractorImpl(private val repository: MoviesRepository) : MoviesInteractor {

    private val executor = Executors.newCachedThreadPool()

    override fun searchMovies(expression: String, consumer: MoviesInteractor.MoviesConsumer) {
        executor.execute {
            when(val resource = repository.searchMovies(expression)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }

    override fun getMoviesDetails(movieId: String, consumer: MoviesInteractor.MovieDetailsConsumer) {
        executor.execute {
            when(val resource = repository.getMovieDetails(movieId)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }

    override fun getMovieCast(movieId: String, consumer: MoviesInteractor.MovieCastConsumer) {
        executor.execute {
            when(val resource = repository.getMovieCast(movieId)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(resource.data, resource.message) }
            }
        }
    }

}
