package nhibien.nguyen.moviesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityNewMovie extends AppCompatActivity {

    //Strings
    private  String title;

    //EditTexts
    private EditText edittxtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_movie);

        //Get the title
        Intent intent = getIntent();
        title = intent.getStringExtra("TITLE");

        //Init EditTexts
        edittxtTitle = (EditText) findViewById(R.id.nm_title);

        //Set the title
        edittxtTitle.setText(title);


    }

    /**
     * Setup the Menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu){
        //Get the menu Layout
        getMenuInflater().inflate(R.menu.menu_new_movie, menu);

        //Get the THUMPS ITEM
        MenuItem thumpsItem = (MenuItem) menu.findItem(R.id.nm_action_thumps_up);


        thumpsItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(ActivityNewMovie.this, "Thumbs Up", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        return true;
    }
}
//--TODO-- RECYCLERVIEW has to update when changing to all list after using the search
