package com.example.rahman.helper;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 05.27.
 * ===================================================
 * Package              : com.example.rahman.helper.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class ApiClient {
    private static ApiClientInterface mService;

    public static ApiClientInterface getInterface() {
        if (mService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constans.BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();


            mService = retrofit.create(ApiClientInterface.class);
        }
        return mService;
    }
}
