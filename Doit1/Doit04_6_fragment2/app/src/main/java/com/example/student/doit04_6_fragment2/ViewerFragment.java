package com.example.student.doit04_6_fragment2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by student on 2018-11-19.
 */

public class ViewerFragment extends Fragment{
    ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup vg = (ViewGroup)inflater.inflate(R.layout.fragment_viewer,container,false);

        imageView = (ImageView)vg.findViewById(R.id.imageView);

        return vg;
    }

    public void setImage(int resId){
        imageView.setImageResource(resId);
    }
}
