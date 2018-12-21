package com.example.student.mymovieapp.format;

import android.graphics.Bitmap;

/**
 * Created by student on 2018-12-14.
 */

public class Movie {

    String title;
    String[] director;
    String[] actor;
    String[] category;
    String runningTime;
    String openDate;
    Bitmap bitmap;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public Movie(String title, String[] director, String[] actor, String[] category, String runningTime, String openDate) {
        this.title = title;
        this.director = director;
        this.actor = actor;
        this.category = category;
        this.runningTime = runningTime;
        this.openDate = openDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getDirector() {
        return director;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public String[] getActor() {
        return actor;
    }

    public void setActor(String[] actor) {
        this.actor = actor;
    }

    public String[] getCategory() {
        return category;
    }

    public void setCategory(String[] category) {
        this.category = category;
    }

    public String getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(String runningTime) {
        this.runningTime = runningTime;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
