package com.example.rahman.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rahman.R;
import com.example.rahman.adapter.SerialAdapter;
import com.example.rahman.helper.ApiClient;
import com.example.rahman.model.Serial;
import com.example.rahman.model.SerialObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.GridLayout.HORIZONTAL;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.48.
 * ===================================================
 * Package              : com.example.rahman.fragment.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class OnTheAirSerialFragment extends Fragment {

    ArrayList<Serial> SerialList;
    SerialAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.now_playing, container, false);
        final RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);

        SerialList = new ArrayList<>();

        adapter = new SerialAdapter(getActivity(), SerialList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 1, HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
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


        Call<SerialObject> jsonObject = ApiClient.getInterface().getOntheAirSerial();

        jsonObject.enqueue(new Callback<SerialObject>() {
            @Override
            public void onResponse(Call<SerialObject> call, Response<SerialObject> response) {
                SerialObject jObj = response.body();

                for (int i = 0; i < jObj.getResults().size(); i++)
                    SerialList.add(jObj.getResults().get(i));

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
