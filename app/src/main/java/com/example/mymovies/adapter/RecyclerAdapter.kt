package com.example.mymovies.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.dataclass.Result
import com.example.mymovies.ui.MovieDetailsActivity
import com.squareup.picasso.Picasso


class RecyclerAdapter(private val context: Context): RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){

    private var MovieList : MutableList<Result>?=null

    fun setMovieList(movies: List<Result>){
        this.MovieList = movies.toMutableList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.single_movie_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return MovieList?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = MovieList?.get(position)

        if (movie != null) {
            holder.bind(movie)
        }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){

        val movieTitle:TextView = view.findViewById(R.id.movieTitle)
        val movieRating: TextView = view.findViewById(R.id.movieRating)
        val movieLanguage:TextView = view.findViewById(R.id.movieLanguage)
        val movieYear:TextView = view.findViewById(R.id.movieYear)
        val movieImage:ImageView = view.findViewById(R.id.movieImage)

        fun bind(movie: Result){

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MovieDetailsActivity::class.java)
                intent.putExtra("movie", movie)
                itemView.context.startActivity(intent)
            }

            val imageUrl = "https://image.tmdb.org/t/p/w500" + movie.poster_path

            Picasso.get().load(imageUrl).into(movieImage)

            movieTitle.text = movie.title
            movieRating.text = movie.vote_average.toString()
            movieLanguage.text = movie.original_language
            movieYear.text = movie.release_date

        }

    }
}