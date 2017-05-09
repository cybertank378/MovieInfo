package com.example.rahman.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rahman.MainActivity;
import com.example.rahman.R;
import com.example.rahman.activity.MovieDetailActivity;
import com.example.rahman.helper.Constans;
import com.example.rahman.model.Movie;

import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.55.
 * ===================================================
 * Package              : com.example.rahman.adapter.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieHolder> {
    String imgBaseUrl = Constans.ImgBaseUrl;
    Context context;

    ArrayList<Movie> movieList;

    public MovieAdapter(Context context, ArrayList<Movie> movieList) {
        this.movieList = movieList;
        this.context = context;
    }
    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_adapter,parent, false);
        return  new MovieHolder(view);

    }

    @Override
    public void onBindViewHolder(final MovieHolder holder, int position) {

        Movie movie = movieList.get(position);
        String posterPath = movie.getPosterPath();

        String genres = "";
        ArrayList<Integer> genreId = movie.getGenreId();
        for (int i=0; i<genreId.size(); i++){
            String genre="";
            int id = genreId.get(i);
            for(int j = 0; j< MainActivity.genresList.size(); j++){
                if(MainActivity.genresList.get(j).getId() == id){
                    genre = MainActivity.genresList.get(j).getName();
                    break;
                }
            }
            genres = genres + genre +" ";
            Log.i("genre data", genre);

            View.OnClickListener onClickListener = new View.OnClickListener() {

                int position = holder.getAdapterPosition();
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(context, MovieDetailActivity.class);
                    intent.putExtra("movie object",movieList.get(position));
                    context.startActivity(intent);
                }
            };

            holder.mMovieImg.setOnClickListener(onClickListener);
            Log.i("genres list", genres);
            holder.mGenre.setText(genres);
            Drawable mDefaultBackground = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_noimage, null);

            Glide.with(context).load(imgBaseUrl + posterPath + "?api_key"+Constans.ApiKey)
                    .error(mDefaultBackground)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.mMovieImg);
        }

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder {
        ImageView mMovieImg;
        TextView mGenre;


        public MovieHolder(View itemView){
            super(itemView);
            mMovieImg = (ImageView)itemView.findViewById(R.id.movieImage);
            mGenre = (TextView) itemView.findViewById(R.id.genre);


        }


    }
}
