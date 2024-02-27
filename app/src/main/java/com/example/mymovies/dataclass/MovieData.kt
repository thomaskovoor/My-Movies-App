package com.example.mymovies.dataclass

data class MovieData(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)