package com.example.rahman.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.54.
 * ===================================================
 * Package              : com.example.rahman.model.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class Movie implements Serializable {

    private int id;
    private String title;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("overview")
    private String description;
    @SerializedName("vote_average")
    private float rating;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("genre_ids")
    private ArrayList<Integer> genreId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public ArrayList<Integer> getGenreId() {
        return genreId;
    }

    public void setGenreId(ArrayList<Integer> genreId) {
        this.genreId = genreId;
    }
}
