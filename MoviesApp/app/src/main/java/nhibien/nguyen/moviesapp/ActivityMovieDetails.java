package nhibien.nguyen.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


/**
 * Created by bien on 03.06.2017.
 */

public class ActivityMovieDetails extends AppCompatActivity{
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);

        Intent intent = getIntent();

        Movie movie = (Movie)intent.getSerializableExtra("MOVIE");
        TextView txtViewTitle = (TextView) findViewById(R.id.mda_title);
        txtViewTitle.setText(movie.getTitle());

    }
}
