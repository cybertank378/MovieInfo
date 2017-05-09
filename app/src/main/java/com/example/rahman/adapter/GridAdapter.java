package com.example.rahman.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.rahman.MainActivity;
import com.example.rahman.R;
import com.example.rahman.helper.Constans;
import com.example.rahman.model.Movie;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 06.49.
 * ===================================================
 * Package              : com.example.rahman.adapter.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class GridAdapter extends BaseAdapter {
    private String imgBaseUrl = Constans.ImgBaseUrl;
    private Context mContext;
    private ArrayList<Movie> mMovies;

    public GridAdapter(Context mContext, ArrayList<Movie> mMovies) {
        this.mContext = mContext;
        this.mMovies = mMovies;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = View.inflate(mContext, R.layout.image_adapter, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        Movie movie = (Movie)getItem(position);
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
            System.out.println("genres list =="+  genres);
        }

        System.out.println("genres list =="+  genres);
        holder.genre.setText(genres);
        Drawable mDefaultBackground = ResourcesCompat.getDrawable(mContext.getResources(), R.drawable.ic_noimage, null);
        Glide.with(mContext).load(imgBaseUrl + posterPath + "?api_key"+Constans.ApiKey).centerCrop()
                .error(mDefaultBackground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .into(holder.movieImg);

        return view;
    }

    static class ViewHolder {
        @InjectView(R.id.genre) TextView genre;
        @InjectView(R.id.movieImage) ImageView movieImg;
        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }



}
