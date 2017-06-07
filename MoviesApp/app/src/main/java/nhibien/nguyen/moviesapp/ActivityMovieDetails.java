package nhibien.nguyen.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by bien on 03.06.2017.
 */

public class ActivityMovieDetails extends AppCompatActivity{

    private Movie movie;
    private Context mContext = this;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        movie = (Movie)intent.getSerializableExtra("MOVIE");
        TextView txtViewTitle = (TextView) findViewById(R.id.mda_title);
        txtViewTitle.setText(movie.getTitle());

    }

    /**
     * Setup the Menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu){
        //Get the menu layout
        getMenuInflater().inflate(R.menu.menu_movie_details, menu);

        //Get the add_item
        MenuItem add_item = (MenuItem) menu.findItem(R.id.mda_action_add);

        //Set the Seen-Icon on the right if the movie was seeen
        if(movie.isSeen()){
            add_item.setIcon(R.drawable.ic_eye_24dp);
        }

        //onClickListener for the add_item
        add_item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(!movie.isSeen()){
                    item.setIcon(R.drawable.ic_eye_24dp);
                    movie.setSeenTrue();
                    ActivityMain.updatedMovie(movie, mContext);
                    Toast.makeText(mContext, movie.getTitle() + " added", Toast.LENGTH_SHORT).show();
                }else{
                    item.setIcon(R.drawable.ic_add_grey_24dp);
                    movie.setSeenFalse();
                    ActivityMain.updatedMovie(movie, mContext);
                    Toast.makeText(mContext, movie.getTitle() + " removed", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
