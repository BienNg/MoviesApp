package nhibien.nguyen.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bien on 23.05.2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{

    //Defining the ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titleTextView;
        public TextView descriptionTextView;
        public ViewHolder(View itemview){
            super(itemview);
            titleTextView = (TextView) itemview.findViewById(R.id.item_title);
            descriptionTextView = (TextView) itemview.findViewById(R.id.item_description);
        }
    }

    //Content that populates the RecyclerView
    private List<Movie> moviesList;
    private Context context;

    //Constructor
    public MoviesAdapter(Context context, List<Movie> movies){
        moviesList = movies;
        this.context = context;
    }

    private Context getContext(){
        return context;
    }

    /*
     * Extending Methods
     */
    @Override
    public MoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the costum Layout
        View movieView = inflater.inflate(R.layout.recyclerview_item, parent, false);

        //Return a new Holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesAdapter.ViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        //set items
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        //set text of item
        titleTextView.setText(movie.getTitle());
        if(movie.getStudio() != null){
            descriptionTextView.setText(movie.getStudio());
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
