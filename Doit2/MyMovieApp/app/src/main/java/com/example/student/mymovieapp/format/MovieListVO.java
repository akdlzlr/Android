package com.example.student.mymovieapp.format;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieListVO extends RealmObject {

    private RealmList<MovieVO> movieList = new RealmList<MovieVO>();

    public RealmList<MovieVO> getMovieList() {
        return movieList;
    }

    public void setMovieList(RealmList<MovieVO> movieList) {
        this.movieList = movieList;
    }
}
