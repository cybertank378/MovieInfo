package com.example.rahman.model;

import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 05.38.
 * ===================================================
 * Package              : com.example.rahman.model.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class MovieTrailerObject {
    private int id;

    private ArrayList<MovieTrailer> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MovieTrailer> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieTrailer> results) {
        this.results = results;
    }
}
