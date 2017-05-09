package com.example.rahman.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;


import com.example.rahman.R;
import com.example.rahman.activity.MovieDetailActivity;
import com.example.rahman.adapter.GridAdapter;
import com.example.rahman.helper.ApiClient;
import com.example.rahman.model.Movie;
import com.example.rahman.model.MovieObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.36.
 * ===================================================
 * Package              : com.example.rahman.fragment.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class UpComingFragment extends Fragment {
    ArrayList<Movie> movieList;
    GridAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toprated_fragment, container, false);
        GridView gv = (GridView) v.findViewById(R.id.top_gridview);

        movieList = new ArrayList<>();

        adapter = new GridAdapter(getActivity(), movieList);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), MovieDetailActivity.class);
                intent.putExtra("movie object",movieList.get(position));
                startActivity(intent);
            }
        });


        Call<MovieObject> jsonObject = ApiClient.getInterface().getUpComingMovies();

        jsonObject.enqueue(new Callback<MovieObject>() {
            @Override
            public void onResponse(Call<MovieObject> call, Response<MovieObject> response) {
                MovieObject mJobj = response.body();

                for (int i = 0; i < mJobj.getMovieList().size(); i++)
                    movieList.add(mJobj.getMovieList().get(i));

                Log.i("movie data", String.valueOf(movieList.size()));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG);

            }
        });

        return v;
    }
}
