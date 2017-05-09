package com.example.rahman.model;

import java.util.ArrayList;

/**
 * Created              : Rahman on 10/05/2017.
 * Date Created         : 10/05/2017 / 05.39.
 * ===================================================
 * Package              : com.example.rahman.model.
 * Project Name         : MovieInfo.
 * Copyright            : Copyright @ 2017 Sudeska.
 */
public class SerialObject {
    private int page;
    private ArrayList<Serial> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<Serial> getResults() {
        return results;
    }

    public void setResults(ArrayList<Serial> results) {
        this.results = results;
    }
}
