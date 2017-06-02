package nhibien.nguyen.moviesapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;

/**
 * Created by bien on 23.05.2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //Content that populates the RecyclerView
    private List<Movie> moviesList;
    //Context
    private Context context;

    //Defining the ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView firstLetterTextView;
        public ViewHolder(View itemview){
            super(itemview);
            titleTextView = (TextView) itemview.findViewById(R.id.item_title);
            descriptionTextView = (TextView) itemview.findViewById(R.id.item_description);
            firstLetterTextView = (TextView) itemview.findViewById(R.id.item_first_letter);
        }
    }

    //Constructor
    public RecyclerViewAdapter(Context context, List<Movie> movies){
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
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the costum Layout
        View movieView = inflater.inflate(R.layout.recyclerview_item, parent, false);

        //Return a new Holder instance
        ViewHolder viewHolder = new ViewHolder(movieView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Movie movie = moviesList.get(position);

        //set items
        TextView firstLetterTextView = holder.firstLetterTextView;
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;

        //set text of item
        titleTextView.setText(movie.getTitle());
        if(movie.getStudio() != null){
            descriptionTextView.setText(movie.getStudio());
        }

        //Set visibility of the first letters on the left
        firstLetterTextView.setVisibility(View.INVISIBLE);
        if(position != 0){
            //if the last title had a different first letter, this firstLetterTextView should be visible
            if(!movie.getTitle().substring(0,1).equalsIgnoreCase(moviesList.get(position-1).getTitle().substring(0,1))){
                firstLetterTextView.setVisibility(View.VISIBLE);
                firstLetterTextView.setText(movie.getTitle().substring(0,1).toUpperCase());
            }
        }else{
            firstLetterTextView.setVisibility(View.VISIBLE);
            firstLetterTextView.setText(movie.getTitle().substring(0,1).toUpperCase());
        }
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    /**
     * Method for the SearchView
     */
    public void setFilter(ArrayList<Movie> newList){
        moviesList = new ArrayList<>();
        moviesList.addAll(newList);

        notifyDataSetChanged();
    }
}
