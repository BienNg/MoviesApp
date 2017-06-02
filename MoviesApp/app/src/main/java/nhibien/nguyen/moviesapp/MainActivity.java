package nhibien.nguyen.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Movie> moviesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        //Add movies to the list
        moviesList = new ArrayList<>();
        moviesList.add(new Movie("a"));
        moviesList.add(new Movie("b"));
        moviesList.add(new Movie("c"));


        /**
         * Setup Recyclerview
         */
        //Create adapter passing the items to the RecyclerView
        MoviesAdapter moviesAdapter = new MoviesAdapter(this, moviesList);
        //Lookup RecyclerView in the Layout
        RecyclerView mainRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mainRecyclerView.setAdapter(moviesAdapter);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
