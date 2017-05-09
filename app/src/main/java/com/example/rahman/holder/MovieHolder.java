package com.example.rahman.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahman.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 01.35.
 * ===================================================
 * Package              : com.example.rahman.holder.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class MovieHolder extends RecyclerView.ViewHolder {

    @InjectView(R.id.movieImage)
    public ImageView mMovieImg;
    @InjectView(R.id.genre)
    public TextView mGenre;

    public MovieHolder(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }
}
