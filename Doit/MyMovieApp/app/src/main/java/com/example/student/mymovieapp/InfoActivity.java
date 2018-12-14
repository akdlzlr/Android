package com.example.student.mymovieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {

    TextView tvName1, tvDate1;
    ImageView ivMovie;
    Button btnBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvName1 = (TextView)findViewById(R.id.tvName1);
        tvDate1 = (TextView)findViewById(R.id.tvDate1);
        ivMovie = (ImageView)findViewById(R.id.ivMovie);
        btnBook = (Button)findViewById(R.id.btnBook);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("movie_name");
        final String date = intent.getStringExtra("movie_date");
        final int id = intent.getIntExtra("movie_id",0);

        tvName1.setText(name);
        tvDate1.setText(date);
        ivMovie.setImageResource(id);

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(InfoActivity.this, BookActivity.class);
                intent1.putExtra("movie_name",name);
                startActivity(intent1);
            }
        });
    }
}
