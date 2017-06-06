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
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * ActivityMain that starts the App
 */
public class ActivityMain extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //Content of the RecyclerView
    private static ArrayList<Movie> moviesList;

    //RecyclerView Adapter
    private RecyclerViewAdapter adapter;

    //STATE of the ADD-BUTTON
    private boolean allList = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        //Add movies to moviesList
        moviesList = new ArrayList<>();
        moviesList.add(new Movie("bbb"));
        moviesList.add(new Movie("aab"));
        moviesList.add(new Movie("aaac"));
        moviesList.add(new Movie("nba"));
        moviesList.add(new Movie("xfactor"));
        moviesList.add(new Movie("x-men"));
        moviesList.add(new Movie("zombieland"));
        for(int i = 0; i<4 ; i++){
            moviesList.get(i).setSeenTrue();
        }


        /**
         * Setup Toolbar
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        //Create the RecyclerView
        updateRecyclerView(moviesList);
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
        //It changes moviesList to allMoviesList
        item_add.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                allList = !allList;
                if(allList){
                    item.setIcon(R.drawable.ic_file_download_grey_24dp);
                    updateRecyclerView(moviesList);
                }else{
                    item.setIcon(R.drawable.ic_add_grey_24dp);
                    ArrayList<Movie> currentList = currentListIsAll(false);
                    updateRecyclerView(currentList);
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
        ArrayList<Movie> currentList;
        if(allList == true){
            currentList = moviesList;
        }else{
            currentList = currentListIsAll(false);
        }
        for(Movie movie: currentList){
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
    private void updateRecyclerView(ArrayList moviesList){
        //Sort the moviesList in alphabetical order
        Collections.sort(moviesList, new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getTitle().compareToIgnoreCase(o2.getTitle());
            }
        });

        if(!allList){
            ArrayList<Movie> currentList = currentListIsAll(false);
            moviesList = currentList;
        }
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

    private ArrayList currentListIsAll(boolean yes){
        if(yes){
            return moviesList;
        }else{
            ArrayList<Movie> newList = new ArrayList<>();
            for(Movie m : moviesList){
                if(m.isSeen()) {
                    newList.add(m);
                }
            }
                return newList;
        }
    }

    /**
     * Updates a Movie in the List
     * @param movie
     * @param context
     */
    public static void updatedMovie(Movie movie, Context context){
        int index = -1;

        //Find index of the movie
        for(Movie m : moviesList){
            if(m.getTitle().equals(movie.getTitle())){
                index = moviesList.indexOf(m);
                break;
            }
        }
        moviesList.set(index,movie);
        Toast.makeText(context,moviesList.get(index).isSeen() + " added ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume(){
        super.onResume();
        updateRecyclerView(moviesList);
    }

}

//--TODO-- BUG: While searching through all movies, unseen movies have green check icons