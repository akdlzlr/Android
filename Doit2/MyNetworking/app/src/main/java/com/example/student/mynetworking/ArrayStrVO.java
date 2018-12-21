package com.example.student.mynetworking;

import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class ArrayStrVO extends RealmObject {
    String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
