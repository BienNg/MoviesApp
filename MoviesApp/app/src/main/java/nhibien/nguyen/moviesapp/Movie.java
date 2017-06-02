package nhibien.nguyen.moviesapp;

import java.util.ArrayList;

/**
 * Created by bien on 27.05.2017.
 */

public class Movie {
    private String title;
    private ArrayList actorsList;
    private String director;
    private String studio;

    //Constructor
    public Movie(String title){
        this.title = title;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void addActors(String actor){
        actorsList.add(actor);
    }
    public void setDirector(String director){
        this.director = director;
    }
    public void setStudio(String studio){
        this.studio = studio;
    }
    public String getTitle(){
        return title;
    }
    public String getDirector(){
        if(director != null){
            return director;
        }
        else{
            return null;
        }
    }
    public String getStudio(){
        if(studio != null) {
            return studio;
        }else{
            return null;
        }
    }
    public ArrayList getActorsList(){
        return actorsList;
    }
}
