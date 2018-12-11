package com.example.student.sampleparcelable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by student on 2018-11-12.
 */

public class SampleData implements Parcelable {
    int number;
    String message;

    public SampleData(int num, String msg) {
        number = num;
        message = msg;
    }

    public SampleData(Parcel src) {
        number = src.readInt();
        message = src.readString();
    }


    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public Object createFromParcel(Parcel in) {
            return new SampleData(in);
        }

        @Override
        public Object[] newArray(int size) {
            return new Object[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel data, int i) {
        data.writeInt(number);
        data.writeString(message);
    }

    public int getNumber() {
        return number;
    }

    public String getMessage() {
        return message;
    }
}
