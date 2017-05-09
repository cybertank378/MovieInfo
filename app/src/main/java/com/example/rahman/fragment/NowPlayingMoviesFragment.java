package com.example.rahman.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rahman.R;
import com.example.rahman.adapter.MovieAdapter;
import com.example.rahman.helper.ApiClient;
import com.example.rahman.model.Movie;
import com.example.rahman.model.MovieObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.48.
 * ===================================================
 * Package              : com.example.rahman.fragment.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class NowPlayingMoviesFragment extends Fragment {
    MovieAdapter movieAdapter;
    ArrayList<Movie> movieList ;
    private View mView;
    @InjectView(R.id.recyclerView)
    RecyclerView mRecycleView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.now_playing, container, false);
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.now_playing, container, false);


        ButterKnife.inject(this, rootView);

        movieList = new ArrayList<>();
        movieAdapter = new MovieAdapter(getActivity(), movieList);
        RecyclerView.LayoutManager rvMgr = new GridLayoutManager(getActivity(), 1, HORIZONTAL, false);
        mRecycleView.setLayoutManager(rvMgr);
        mRecycleView.setAdapter(movieAdapter);

        mRecycleView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            int offset = mRecycleView.computeVerticalScrollOffset();
            int extent = mRecycleView.computeVerticalScrollExtent();
            int range = mRecycleView.computeVerticalScrollRange();

            int percentage = (int)(100.0 * offset / (float)(range - extent));
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        Call<MovieObject> jObject = ApiClient.getInterface().getNowPlayingMovies();

        jObject.enqueue(new Callback<MovieObject>(){
            @Override
            public void onResponse(Call<MovieObject> call, Response<MovieObject> response) {
                MovieObject jObj = response.body();

                for (int i = 0; i < jObj.getMovieList().size(); i++)
                    movieList.add(jObj.getMovieList().get(i));

                Log.i("movie data", String.valueOf(movieList.size()));

                movieAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<MovieObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG);

            }
        });

        return rootView;
    }







}
