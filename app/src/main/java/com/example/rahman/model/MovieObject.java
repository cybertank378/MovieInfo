package com.example.rahman.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 04.52.
 * ===================================================
 * Package              : com.example.rahman.model.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class MovieObject {
    int page;
    @SerializedName("results")
    ArrayList<Movie> movieList;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(ArrayList<Movie> movieList) {
        this.movieList = movieList;
    }
}
