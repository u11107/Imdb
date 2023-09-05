package ru.yandex.practicum.moviessearch.data

import ru.yandex.practicum.moviessearch.data.converters.MovieCastConverter
import ru.yandex.practicum.moviessearch.data.dto.MovieCastRequest
import ru.yandex.practicum.moviessearch.data.dto.MovieCastResponse
import ru.yandex.practicum.moviessearch.data.dto.MovieDetailsRequest
import ru.yandex.practicum.moviessearch.data.dto.MovieDetailsResponse
import ru.yandex.practicum.moviessearch.data.dto.MoviesSearchRequest
import ru.yandex.practicum.moviessearch.data.dto.MoviesSearchResponse
import ru.yandex.practicum.moviessearch.domain.api.MoviesRepository
import ru.yandex.practicum.moviessearch.domain.models.Movie
import ru.yandex.practicum.moviessearch.domain.models.MovieCast
import ru.yandex.practicum.moviessearch.domain.models.MovieDetails
import ru.yandex.practicum.moviessearch.util.Resource

class MoviesRepositoryImpl(
    private val networkClient: NetworkClient,
    private val movieCastConverter: MovieCastConverter,
) : MoviesRepository {

    override fun searchMovies(expression: String): Resource<List<Movie>> {
        val response = networkClient.doRequest(MoviesSearchRequest(expression))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as MoviesSearchResponse) {
                    Resource.Success(results.map {
                        Movie(it.id, it.resultType, it.image, it.title, it.description)
                    })
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")
            }
        }
    }

    override fun getMovieDetails(movieId: String): Resource<MovieDetails> {
        val response = networkClient.doRequest(MovieDetailsRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                with(response as MovieDetailsResponse) {
                    Resource.Success(
                        MovieDetails(
                            id, title, imDbRating ?: "", year,
                            countries, genres, directors, writers, stars, plot
                        )
                    )
                }
            }
            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

    override fun getMovieCast(movieId: String): Resource<MovieCast> {
        val response = networkClient.doRequest(MovieCastRequest(movieId))
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте подключение к интернету")
            }
            200 -> {
                Resource.Success(
                    data = movieCastConverter.convert(response as MovieCastResponse)
                )
            }
            else -> {
                Resource.Error("Ошибка сервера")

            }
        }
    }

}
