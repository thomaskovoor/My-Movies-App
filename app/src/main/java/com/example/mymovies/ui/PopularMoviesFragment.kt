package com.example.mymovies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovies.R
import com.example.mymovies.adapter.RecyclerAdapter
import com.example.mymovies.viewmodel.PopularMoviesViewModel


class PopularMoviesFragment : Fragment() {

    private lateinit var recyclerAdapter : RecyclerAdapter
    private var pageNum = 1


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_popular_movies, container, false)

        val viewModel = ViewModelProvider(this)[PopularMoviesViewModel::class.java]


        viewModel.fetchMovies(pageNum)

        val recyclerView = view.findViewById<RecyclerView>(R.id.popRecView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerAdapter= RecyclerAdapter(requireContext())
        recyclerView.adapter = recyclerAdapter


        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val totalItemCount = layoutManager.itemCount
                val lastVisibleItem = layoutManager.findLastVisibleItemPosition()

                if (totalItemCount <= lastVisibleItem+2) {
                    pageNum++
                    viewModel.fetchMovies(pageNum)
                }
            }
        })


        viewModel.moviesLiveData.observe(viewLifecycleOwner) {
            recyclerAdapter.setMovieList(it)
            recyclerAdapter.notifyDataSetChanged()
        }


        return view
    }


}