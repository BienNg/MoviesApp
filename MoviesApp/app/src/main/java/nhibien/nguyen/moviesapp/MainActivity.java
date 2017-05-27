package nhibien.nguyen.moviesapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private LinearLayoutManager mainLinearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        mainRecyclerView = (RecyclerView)findViewById(R.id.main_recycler_view);
        mainLinearLayoutManager = new LinearLayoutManager(this);
        mainRecyclerView.setLayoutManager(mainLinearLayoutManager);
    }
}
