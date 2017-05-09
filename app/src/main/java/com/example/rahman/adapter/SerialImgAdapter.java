package com.example.rahman.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
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
import com.example.rahman.model.Serial;

import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 04.38.
 * ===================================================
 * Package              : com.example.rahman.adapter.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class SerialImgAdapter extends BaseAdapter {

    String imgBaseUrl = Constans.ImgBaseUrl;

    Context context;
    ArrayList<Serial> movies;

    public SerialImgAdapter(Context context, ArrayList<Serial> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public class ViewHolder {
        ImageView movieImage;
        TextView genre;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = View.inflate(context, R.layout.image_adapter, null);

            ViewHolder vh = new ViewHolder();

            vh.genre = (TextView) convertView.findViewById(R.id.genre);
            vh.movieImage = (ImageView) convertView.findViewById(R.id.movieImage);

            convertView.setTag(vh);
        }

        ViewHolder vh = (ViewHolder) convertView.getTag();

        Serial movie = (Serial)getItem(position);
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
        }

        Log.i("genres list", genres);
        vh.genre.setText(genres);

        Drawable mDefaultBackground = ResourcesCompat.getDrawable(context.getResources(), R.drawable.ic_noimage, null);
        Glide.with(context).load(imgBaseUrl + posterPath + "?api_key"+ Constans.ApiKey)
                .error(mDefaultBackground)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(vh.movieImage);


        return convertView;
    }
}
