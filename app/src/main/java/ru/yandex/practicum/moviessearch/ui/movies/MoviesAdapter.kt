package ru.yandex.practicum.moviessearch.ui.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.yandex.practicum.moviessearch.domain.models.Movie

class MoviesAdapter(val clickListener: MovieClickListener) : RecyclerView.Adapter<MovieViewHolder>() {

    var movies = ArrayList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder = MovieViewHolder(parent)

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies.get(position))
        holder.itemView.setOnClickListener { clickListener.onMovieClick(movies.get(position)) }
    }

    override fun getItemCount(): Int = movies.size

    fun interface MovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}