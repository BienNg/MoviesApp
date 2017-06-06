package nhibien.nguyen.moviesapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter Class for the RecyclerView in the ActivityMain
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    //Content that populates the RecyclerView
    private List<Movie> moviesList;
    //Context
    private Context context;

    //Defining the ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //DECL. VIEWS of the item
        private TextView titleTextView;
        private TextView descriptionTextView;
        private TextView firstLetterTextView;
        private ImageView seenIcon;
        public ViewHolder(View itemview){
            super(itemview);
            //GET VIEWS of the LAYOUT
            titleTextView = (TextView) itemview.findViewById(R.id.item_title);
            descriptionTextView = (TextView) itemview.findViewById(R.id.item_description);
            firstLetterTextView = (TextView) itemview.findViewById(R.id.item_first_letter);
            seenIcon = (ImageView) itemview.findViewById(R.id.item_seen_image);
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
     * Extending Method From RecyclerView Adapter 1/2
     */
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        //Inflate the costum Layout
        final View movieView = inflater.inflate(R.layout.recyclerview_item, parent, false);

        //Return a new Holder instance
        final ViewHolder viewHolder = new ViewHolder(movieView);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get position
                int position = viewHolder.getAdapterPosition();
                //Get the movie
                Movie movie = moviesList.get(position);
                //Check if an item was deleted
                if(position != RecyclerView.NO_POSITION){
                    Intent intent = new Intent(context, ActivityMovieDetails.class);
                    intent.putExtra("MOVIE", movie);
                    context.startActivity(intent);
                }
            }
        });

        return viewHolder;
    }

    /*
     * Extending Method From RecyclerView Adapter 2/2
     */
    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        //GET THE MOVIE for this position
        Movie movie = moviesList.get(position);

        //Init. Views of the item
        TextView firstLetterTextView = holder.firstLetterTextView;
        TextView titleTextView = holder.titleTextView;
        TextView descriptionTextView = holder.descriptionTextView;
        ImageView seenIcon = holder.seenIcon;

        //Set TEXT of the ITEMS
        titleTextView.setText(movie.getTitle());
        if(movie.getStudio() != null){
            descriptionTextView.setText(movie.getStudio());
        }

        //Set visibility of the FIRST LETTERS on the left
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

        //Set COLOR of the SEEN-ICON
        if(movie.isSeen() == true){
            seenIcon.setImageResource(R.drawable.ic_check_green_24dp);
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
