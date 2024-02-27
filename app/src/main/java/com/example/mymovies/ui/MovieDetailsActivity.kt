package com.example.mymovies.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.mymovies.R
import com.example.mymovies.dataclass.Result
import com.squareup.picasso.Picasso

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        val movieDetailsActivity = intent.getParcelableExtra<Result>("movie")

        val movieImage:ImageView = findViewById(R.id.movieImage)
        val movieTitle = findViewById<TextView>(R.id.movieTitle)
        val movieOverview = findViewById<TextView>(R.id.movieOverview)
        val movieReleaseDate = findViewById<TextView>(R.id.movieReleaseDate)
        val movieLanguage = findViewById<TextView>(R.id.movieLanguage)
        val moviePopularity = findViewById<TextView>(R.id.moviePopularity)
        val movieVoteAverage = findViewById<TextView>(R.id.movieVoteAverage)
        val movieVoteCount = findViewById<TextView>(R.id.movieVoteCount)

        val imageUrl = "https://image.tmdb.org/t/p/w500" + movieDetailsActivity?.poster_path

        Picasso.get().load(imageUrl).into(movieImage)
        movieTitle.text = movieDetailsActivity?.title
        movieOverview.text = movieDetailsActivity?.overview
        movieReleaseDate.text = getString(R.string.release_date, movieDetailsActivity?.release_date)
        movieLanguage.text = getString(R.string.language, movieDetailsActivity?.original_language)
        moviePopularity.text = getString(R.string.popularity, movieDetailsActivity?.popularity.toString())
        movieVoteAverage.text = getString(R.string.vote_average, movieDetailsActivity?.vote_average.toString())
        movieVoteCount.text = getString(R.string.vote_count, movieDetailsActivity?.vote_count.toString())



    }
}