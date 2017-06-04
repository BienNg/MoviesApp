package nhibien.nguyen.moviesapp;

import android.content.Context;
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

/**
 * ActivityMain that starts the App
 */
public class ActivityMain extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //Content of the RecyclerView
    ArrayList<Movie> myMoviesList;
    ArrayList<Movie> allMoviesList;

    //RecyclerView Adapter
    RecyclerViewAdapter adapter;

    //Context for the RecyclerView
    Context mContext = this;

    //STATE of the ADD-BUTTON
    private boolean allList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        //Add movies to myMoviesList
        myMoviesList = new ArrayList<>();
        myMoviesList.add(new Movie("bbb"));
        myMoviesList.add(new Movie("aab"));
        myMoviesList.add(new Movie("aaac"));
        for(Movie m : myMoviesList){
            m.setSeenTrue();
        }
        //Add movies to allMoviesList
        allMoviesList = new ArrayList<>();
        allMoviesList.add(new Movie("bbb"));
        allMoviesList.add(new Movie("aab"));
        allMoviesList.add(new Movie("aaac"));
        allMoviesList.add(new Movie("nba"));
        allMoviesList.add(new Movie("xfactor"));
        allMoviesList.add(new Movie("x-men"));
        allMoviesList.add(new Movie("zombieland"));


        /**
         * Setup Toolbar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        //Create the RecyclerView
        updateRecyclerView(myMoviesList);
    }

    /**
     * Is called when the menu is created
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);

        //Init. menu items
        MenuItem item_search = menu.findItem(R.id.action_search);
        MenuItem item_add = menu.findItem(R.id.action_add_movie);

        //Declare the search item as searchview
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(item_search);
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);

        //Set onClickListener for the add Button
        //It changes myMoviesList to allMoviesList
        item_add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                allList = !allList;
                if(allList){
                    item.setIcon(R.drawable.ic_file_download_grey_24dp);
                    updateRecyclerView(allMoviesList);
                }else{
                    item.setIcon(R.drawable.ic_add_grey_24dp);
                    updateRecyclerView(myMoviesList);
                }

                return true;
            }
        });
        return true;
    }
    /**
     * SearchView Method 1/2
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }
    /**
     * SearchView Method 2/2
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        newText = newText.toLowerCase();
        ArrayList<Movie> newList = new ArrayList<>();
        for(Movie movie: myMoviesList){
            String name = movie.getTitle();
            if(name.contains(newText)){
                newList.add(movie);
            }
        }
        adapter.setFilter(newList);
        return true;
    }

    /**
     * Change content of the RecyclerView
     */
    private void updateRecyclerView(ArrayList newList){
        //Sort the myMoviesList in alphabetical order
        Collections.sort(newList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });
        /**
         * Setup Recyclerview
         */
        //Create adapter passing the items to the RecyclerView
        adapter = new RecyclerViewAdapter(this, newList);
        //Lookup RecyclerView in the Layout
        RecyclerView mainRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mainRecyclerView.setAdapter(adapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setHasFixedSize(true);
    }
}