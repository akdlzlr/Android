package com.example.student.mymovieapp.format;

/**
 * Created by student on 2018-12-14.
 */

public class Movie {

    String name;
    String date;
    int img_id;

    public Movie(int img_id) {
        this.img_id = img_id;
    }

    public Movie(String name, String date, int id) {
        this.name = name;
        this.date = date;
        this.img_id = id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return img_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.img_id = id;
    }
}
