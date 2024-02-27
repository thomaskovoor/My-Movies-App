package com.example.mymovies.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.adapter.RecyclerAdapter
import com.example.mymovies.viewmodel.TopRatedMoviesViewModel


class TopRatedMoviesFragment : Fragment() {


    private lateinit var recyclerAdapter : RecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_top_rated_movies, container, false)

        val viewModel = ViewModelProvider(this)[TopRatedMoviesViewModel::class.java]


        viewModel.fetchMovies()

        val recyclerView = view.findViewById<RecyclerView>(R.id.topRecView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerAdapter= RecyclerAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter


        viewModel.moviesLiveData.observe(viewLifecycleOwner) {
            recyclerAdapter.setMovieList(it)
            recyclerAdapter.notifyDataSetChanged()
        }



        return view
    }


}