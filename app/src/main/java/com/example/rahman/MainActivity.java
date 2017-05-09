package com.example.rahman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rahman.fragment.HomeFragment;
import com.example.rahman.fragment.PopularFragment;
import com.example.rahman.fragment.PopularSerialFragment;
import com.example.rahman.fragment.SearchFragment;
import com.example.rahman.fragment.TopFragment;
import com.example.rahman.fragment.TopSerialFragment;
import com.example.rahman.fragment.UpComingFragment;
import com.example.rahman.helper.ApiClient;
import com.example.rahman.model.Genre;
import com.example.rahman.model.GenreObject;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Genre> genresList;
    String mSearchValue="";
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.fab)
    FloatingActionButton mFab;
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        setSupportActionBar(mToolbar);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Fragment fragment = new HomeFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();


        genresList = new ArrayList<>();
        Call<GenreObject> genreJsonObjectCall = ApiClient.getInterface().getGenreObject();
        genreJsonObjectCall.enqueue(new Callback<GenreObject>() {
            @Override
            public void onResponse(Call<GenreObject> call, Response<GenreObject> response) {
                GenreObject genreJsonObject = response.body();

                for(int i=0; i<genreJsonObject.getGenres().size(); i++){
                    genresList.add(genreJsonObject.getGenres().get(i));
                }

                Log.i("genreobject", String.valueOf(genresList.size()));
            }

            @Override
            public void onFailure(Call<GenreObject> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment;
        int id = item.getItemId();

        if(id == R.id.home){
            fragment = new HomeFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack(null).commit();
            setTitle(getResources().getString(R.string.app_name));

        } else
        if (id == R.id.movieTop) {
            fragment = new TopFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack(null).commit();
            setTitle("Top Movies");
        } else if (id == R.id.movieUpcoming) {
            fragment = new UpComingFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack(null).commit();
            setTitle("Upcoming Movies");

        } else if (id == R.id.moviePopular) {
            fragment = new PopularFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack(null).commit();
            setTitle("Popular movies");

        } else if (id == R.id.serialTop) {
            fragment = new TopSerialFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack(null).commit();
            setTitle("Top Serial shows");

        } else if (id == R.id.serialPopular) {

            fragment = new PopularSerialFragment();
            getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).addToBackStack(null).commit();
            setTitle("Popular Serial Shows");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        SearchView mSearch = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.serachAction));
        mSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                mSearchValue =  query;
                Fragment fragment = new SearchFragment();
                Bundle bundle = new Bundle();
                bundle.putString("search text", mSearchValue);
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_frame, fragment).commit();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }
}
