package com.example.student.mymovieapp.format;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieVO extends RealmObject{
    private Movie movieItem;

    public Movie getMovieItem() {
        return movieItem;
    }

    public void setMovieItem(Movie movieItem) {
        this.movieItem = movieItem;
    }
}
