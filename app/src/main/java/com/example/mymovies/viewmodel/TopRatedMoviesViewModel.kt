package com.example.mymovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymovies.dataclass.MovieData
import com.example.mymovies.dataclass.Result
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class TopRatedMoviesViewModel: ViewModel(){

    private val _moviesLiveData = MutableLiveData<List<Result>>()
    var moviesLiveData = _moviesLiveData

    fun fetchMovies() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/movie/top_rated?language=en-US&page=1")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIxYWUxYTNkODA0YTk5ZDZhMjY1ZjBmZTA3OTUwMjc1OCIsInN1YiI6IjY1ZGQ5ZjJmODlkOTdmMDE2Mzk4YWFiNyIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WacJ1JJU5MzFJ_AMl-XSlfvQD7-VLIu6OG0Wtv5QTqI")
                .build()

            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                val jsonData = response.body!!.string()
                val gson = Gson()
                val movieData = gson.fromJson(jsonData, MovieData::class.java)
                moviesLiveData.postValue(movieData.results)

            }
        }
    }

}