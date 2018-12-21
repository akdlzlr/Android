package com.example.student.myrealem;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieVO extends RealmObject {
    private int num;
    private String title;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
