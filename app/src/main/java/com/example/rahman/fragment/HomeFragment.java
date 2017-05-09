package com.example.rahman.fragment;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rahman.R;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 02.35.
 * ===================================================
 * Package              : com.example.rahman.fragment.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class HomeFragment extends Fragment {
    private View mView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.home_fragment, container, false);
        Fragment fragment = new NowPlayingMoviesFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.movieFrame, fragment).commit();

        Fragment fragment1 = new OnTheAirSerialFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.serialFrame, fragment1).commit();
        return mView;

    }


}
