package com.example.student.mymovieapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.student.mymovieapp.format.BookData;
import com.example.student.mymovieapp.format.Movie;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class BookActivity extends AppCompatActivity {

    TextView tvDate, tvTime, tvInfoName, tvInfoDirector, tvInfoActor, tvInfoType, tvSeat;
    Button btnDate, btnTime, btnSeatClear, btnCancle, btnBook;
    SeekBar seatSeekBar;
    int year, month, day, hour, min, num;
    String name;
    BookData bookData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        tvSeat = (TextView) findViewById(R.id.tvSeat);
        tvInfoName = (TextView) findViewById(R.id.tvInfoName);
        tvInfoDirector = (TextView) findViewById(R.id.tvInfoDirector);
        tvInfoActor = (TextView) findViewById(R.id.tvInfoActor);
        tvInfoType = (TextView) findViewById(R.id.tvInfoType);

        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnSeatClear = (Button) findViewById(R.id.btnSeatClear);
        btnCancle = (Button) findViewById(R.id.btnCancle);
        btnBook = (Button) findViewById(R.id.btnBook);

        seatSeekBar = (SeekBar) findViewById(R.id.seatSeekBar);

        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        btnBook.setEnabled(false);

        final Intent intent = getIntent();
        int id = intent.getIntExtra("movie_id", 0);
        name = intent.getStringExtra("movie_name");

        ArrayList<BookData> movieList = new ArrayList<BookData>();

        movieList.add(new BookData("13:30", 30, 30,
                "2", "블랙팬서", "2018-12-11"));

        movieList.add(new BookData("13:30", 30, 28,
                "3", "리틀포레스트", "2018-12-11"));

        movieList.add(new BookData("13:30", 30, 10,
                "2:30", "궁합", "2018-12-11"));

        movieList.add(new BookData("13:30", 20, 10,
                "3:30", "월요일이 사라졌다", "2018-12-11"));


        for (int i = 0; i < movieList.size(); i++) {
            if (name.equals(movieList.get(i).getMovieName())) {
                bookData = movieList.get(i);
            }
        }

        tvInfoName.setText(name);

        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookActivity.this,
                        dateSetListener, year, month, day).show();
            }
        });

        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(BookActivity.this, timeSetListener, hour, min, false).show();
            }
        });

        btnSeatClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seatSeekBar.setProgress(0);
            }
        });

        seatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvSeat.setText("좌석 수 : " + progress);
                num = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int restSeat = bookData.getMovieTotalSeat()-bookData.getMovieBookSeat();

                if(tvDate.getText().toString().equals("날짜 필수")||tvTime.getText().toString().equals("시간 필수")){
                    Toast.makeText(getApplicationContext(), "날짜와 시간은 필수 입니다.",Toast.LENGTH_SHORT).show();
                    btnBook.setEnabled(false);
                    return;
                }

                if(num==0){
                    Toast.makeText(getApplicationContext(),"좌석을 선택해주세요.",Toast.LENGTH_SHORT).show();
                    btnBook.setEnabled(false);
                    return;
                }

                if (1 > restSeat) {
                    Toast.makeText(getApplicationContext(), "남은 좌석이 없습니다.", Toast.LENGTH_SHORT).show();
                    btnBook.setEnabled(false);
                }else if(num>restSeat){
                    Toast.makeText(getApplicationContext(), "잔여 좌석이 부족합니다. \n최대 예약 가능 좌석 수 : "+restSeat, Toast.LENGTH_SHORT).show();
                    btnBook.setEnabled(false);
                }
                else{
                    Toast.makeText(getApplicationContext(), num + " 석 예약 가능 합니다.", Toast.LENGTH_SHORT).show();
                    btnBook.setEnabled(true);
                }
            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(BookActivity.this, ReservationActivity.class);
                startActivity(intent1);
            }
        });
    }


    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            // 월은 0부터 시작된다
            String date = i + "-" + (i1 + 1) + "-" + i2;

            if (date.equals(bookData.getMovieDate())) {
                Toast.makeText(getApplicationContext(), "시간을 선택해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "해당 날짜엔 상영하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }


            tvDate.setText(date);
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {

            String time = i + ":" + i1;

            if (time.equals(bookData.getMovieStartTime())) {
                Toast.makeText(getApplicationContext(), "좌석을 선택해주세요.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "해당 시간엔 상영하지 않습니다.", Toast.LENGTH_SHORT).show();
                return;
            }

            tvTime.setText(time);
        }
    };
}
