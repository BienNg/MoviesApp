package nhibien.nguyen.moviesapp;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //Toolbar
    Toolbar toolbar;

    //Content of the RecyclerView
    ArrayList<Movie> moviesList;

    //RecyclerView Adapter
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        //Add movies to the list
        moviesList = new ArrayList<>();
        moviesList.add(new Movie("bbb"));
        moviesList.add(new Movie("aab"));
        moviesList.add(new Movie("aaac"));

        //Sort the moviesList in alphabetical order
        Collections.sort(moviesList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });

        /**
         * Setup Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        /**
         * Setup Recyclerview
         */
        //Create adapter passing the items to the RecyclerView
        adapter = new RecyclerViewAdapter(this, moviesList);
        //Lookup RecyclerView in the Layout
        RecyclerView mainRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setHasFixedSize(true);
    }

    /**
     * SearchView Method 1/3
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * SearchView Method 2/3
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    /**
     * SearchView Method 3/3
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Movie> newList = new ArrayList<>();
        for(Movie movie:moviesList){
            String name = movie.getTitle();
            if(name.contains(newText)){
                newList.add(movie);
            }
        }
        adapter.setFilter(newList);
        return true;
    }
}
