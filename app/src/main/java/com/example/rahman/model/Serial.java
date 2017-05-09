package com.example.rahman.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.24.
 * ===================================================
 * Package              : com.example.rahman.model.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class Serial implements Serializable {
    @SerializedName("poster_path")
    private String posterPath;
    private int id;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("overview")
    private String overView;
    private String name;
    @SerializedName("genre_ids")
    private ArrayList<Integer> genreId;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getOverView() {
        return overView;
    }

    public void setOverView(String overView) {
        this.overView = overView;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(ArrayList<Integer> genreId) {
        this.genreId = genreId;
    }
}
