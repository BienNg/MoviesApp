package nhibien.nguyen.moviesapp;

import java.util.ArrayList;

/**
 * Class for Persons
 */

public class Person {
    private String name;
    private ArrayList<Movie>actorsList;
    private ArrayList<Movie>directorsList;
    private String age;

    //Constructor
    public Person(String name){
        this.name = name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void addToActorsList(Movie movie){
        actorsList.add(movie);
    }
    public void addToDirectorsList(Movie movie){
        directorsList.add(movie);
    }
    public void setAge(String age){
        this.age = age;
    }
    public String getName(){
        return name;
    }
    public String getAge(){
        return age;
    }
    public ArrayList getActorsList(){
        return actorsList;
    }
    public ArrayList getDirectorsList(){
        return directorsList;
    }
}
