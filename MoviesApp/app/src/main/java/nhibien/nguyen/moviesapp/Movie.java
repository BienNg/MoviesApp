package nhibien.nguyen.moviesapp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by bien on 27.05.2017.
 */

public class Movie implements Serializable{
    private String title;
    private ArrayList<Person> actorsList;
    private Person director;
    private String studio;
    private boolean seen;

    //Constructor
    public Movie(String title){
        this.title = title;
        seen = false;
    }

    public void setTitle(String title){
        this.title = title;
    }
    public void addActors(Person actor){
        actorsList.add(actor);
    }
    public void setDirector(Person director){
        this.director = director;
    }
    public void setStudio(String studio){
        this.studio = studio;
    }
    public void setSeenTrue(){this.seen = true;}
    public void setSeenFalse(){this.seen = false;}
    public String getTitle(){
        return title;
    }
    public Person getDirector(){
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
    public boolean isSeen(){return seen;}
}
