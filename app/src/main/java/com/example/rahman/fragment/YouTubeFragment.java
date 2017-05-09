package com.example.rahman.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rahman.R;
import com.example.rahman.helper.ApiClient;
import com.example.rahman.model.MovieTrailer;
import com.example.rahman.model.MovieTrailerObject;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 04.52.
 * ===================================================
 * Package              : com.example.rahman.activity.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class YouTubeFragment extends Fragment {
    private static final String YOU_TUBE_API_KEY = "AIzaSyBDbsaNCv5vNvLSFbcKftEUUy8iel5v__A";

    ArrayList<MovieTrailer> trailerList;
    ArrayList<String> key= new ArrayList<>();

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.youtube_fragment, container, false);

        final Bundle bundle = getArguments();

        int id = bundle.getInt("movieId");

        trailerList = new ArrayList<>();


        Call<MovieTrailerObject> jsonTrailerObject = ApiClient.getInterface().getTrailerObject(id);

        jsonTrailerObject.enqueue(new Callback<MovieTrailerObject>() {
            @Override
            public void onResponse(Call<MovieTrailerObject> call, Response<MovieTrailerObject> response) {
                MovieTrailerObject jObj = response.body();

                for (int i = 0; i < jObj.getResults().size(); i++) {
                    trailerList.add(jObj.getResults().get(i));

                    for(int j=0; j<trailerList.size(); j++)
                        key.add(trailerList.get(j).getKey());

                    YouTubePlayerFragment tubePlayerFragment = YouTubePlayerFragment.newInstance();

                    FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                    fragmentTransaction.add(R.id.youtube_frame, tubePlayerFragment).commit();


                    tubePlayerFragment.initialize(YOU_TUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                            if(key.size() != 0){
                                if (!b) {
                                    youTubePlayer.cueVideo(key.get(0));
                                }
                            }
                            else {
                                if (!b) {
                                    youTubePlayer.cueVideo("dKrVegVI0Us");
                                }
                            }

                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                        }
                    });
                }


                Log.i("trailer data", String.valueOf(trailerList.size()));
            }

            @Override
            public void onFailure(Call<MovieTrailerObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG);
            }
        });








        return v;
    }
}
