package com.example.rahman.activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.rahman.R;
import com.example.rahman.fragment.YouTubeSerialFragment;
import com.example.rahman.model.Serial;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.28.
 * ===================================================
 * Package              : com.example.rahman.activity.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class SerialDetailActivity extends AppCompatActivity {
    TextView genre, overview, rate, moviename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        genre = (TextView) findViewById(R.id.genre);
        overview = (TextView) findViewById(R.id.overview);
        rate = (TextView) findViewById(R.id.starRate);
        moviename = (TextView) findViewById(R.id.movieName);


        Intent intent = getIntent();
        Serial movie = (Serial) intent.getSerializableExtra("movie object");
        int id = movie.getId();
        String movieName = movie.getName();

        Fragment fragment = new YouTubeSerialFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("movieId", id);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();
        setTitle(movieName + ": Trailer");

        overview.setText(movie.getOverView());
        rate.setText(String.valueOf(movie.getVoteAverage()));
        moviename.setText(movie.getName());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.serachAction);
        item.setVisible(false);
        return true;
    }
}
