package com.example.student.mynetworking;

import android.util.Log;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieVO extends RealmObject{
    private String title;
    private RealmList<ArrayStrVO> directory = new RealmList<ArrayStrVO>();
    private RealmList<ArrayStrVO> actor = new RealmList<ArrayStrVO>();
    private RealmList<ArrayStrVO> category = new RealmList<ArrayStrVO>();
    private String runningTime;
    private String openDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RealmList<ArrayStrVO> getDirectory() {
        return directory;
    }

    public void setDirectory(RealmList<ArrayStrVO> directory) {
        this.directory.addAll(directory);
    }

    public RealmList<ArrayStrVO> getActor() {
        return actor;
    }

    public void setActor(RealmList<ArrayStrVO> actor) {
        this.actor.addAll(actor);
    }

    public RealmList<ArrayStrVO> getCategory() {
        return category;
    }

    public void setCategory(RealmList<ArrayStrVO> category) {
        this.category.addAll(category);
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
