package com.example.rahman.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.rahman.R;
import com.example.rahman.activity.SerialDetailActivity;
import com.example.rahman.adapter.SerialAdapter;
import com.example.rahman.adapter.SerialImgAdapter;
import com.example.rahman.helper.ApiClient;
import com.example.rahman.model.Serial;
import com.example.rahman.model.SerialObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.37.
 * ===================================================
 * Package              : com.example.rahman.fragment.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class PopularSerialFragment extends Fragment {

    ArrayList<Serial> SerialList;
    SerialImgAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.toprated_fragment, container, false);
        GridView gv = (GridView) v.findViewById(R.id.top_gridview);

        SerialList = new ArrayList<>();

        adapter = new SerialImgAdapter(getActivity(), SerialList);
        gv.setAdapter(adapter);


        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.setClass(getActivity(), SerialDetailActivity.class);
                intent.putExtra("movie object",SerialList.get(position));
                startActivity(intent);
            }
        });


        Call<SerialObject> jsonObject = ApiClient.getInterface().getPopularSerial();

        jsonObject.enqueue(new Callback<SerialObject>() {
            @Override
            public void onResponse(Call<SerialObject> call, Response<SerialObject> response) {
                SerialObject jsonObject1 = response.body();

                for (int i = 0; i < jsonObject1.getResults().size(); i++)
                    SerialList.add(jsonObject1.getResults().get(i));

                Log.i("movie data", String.valueOf(SerialList.size()));

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<SerialObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_LONG);

            }
        });

        return v;
    }
}
