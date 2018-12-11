package com.example.student.doit06_5_painboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by student on 2018-11-20.
 */

public class PaintBoardActivity extends AppCompatActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PaintBoard board = new PaintBoard(this);

        setContentView(board);
    }

}
