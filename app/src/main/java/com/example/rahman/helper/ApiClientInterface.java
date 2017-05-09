package com.example.rahman.helper;

import com.example.rahman.model.GenreObject;
import com.example.rahman.model.MovieObject;
import com.example.rahman.model.MovieTrailerObject;
import com.example.rahman.model.SerialObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 05.30.
 * ===================================================
 * Package              : com.example.rahman.helper.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public interface ApiClientInterface {

    @GET("movie/top_rated?api_key=" + Constans.ApiKey )
    Call<MovieObject> getJsonObject();

    @GET("search/movie?api_key=" + Constans.ApiKey)
    Call<MovieObject> searchMovie(@Query("query") String movieName);

    @GET("genre/movie/list?api_key=" + Constans.ApiKey)
    Call<GenreObject> getGenreObject();

    @GET("movie/upcoming?api_key=" + Constans.ApiKey)
    Call<MovieObject> getUpComingMovies();

    @GET("movie/popular?api_key=" + Constans.ApiKey)
    Call<MovieObject> getPopularMovies();

    @GET("movie/{id}/videos?api_key=" + Constans.ApiKey)
    Call<MovieTrailerObject> getTrailerObject(@Path("id") int id);

    @GET("movie/now_playing?api_key=" + Constans.ApiKey)
    Call<MovieObject> getNowPlayingMovies();

    @GET("tv/top_rated?api_key=" + Constans.ApiKey)
    Call<SerialObject> getTopSerial();

    @GET("tv/popular?api_key="+ Constans.ApiKey)
    Call<SerialObject> getPopularSerial();

    @GET("tv/on_the_air?api_key=" + Constans.ApiKey)
    Call<SerialObject> getOntheAirSerial();

    @GET("tv/{id}/videos?api_key=" + Constans.ApiKey)
    Call<SerialObject> getSerialTrailerObject(@Path("id") int id);
}
