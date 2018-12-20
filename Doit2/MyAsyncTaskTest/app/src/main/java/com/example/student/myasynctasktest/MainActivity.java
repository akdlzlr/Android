package com.example.student.myasynctasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int value;
    TextView tv1;
    Button btn1, btn2;
    ProgressBar pb1;


    MyTask myTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.tv1);
        pb1 = (ProgressBar)findViewById(R.id.pb1);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        pb1.setVisibility(View.INVISIBLE);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myTask == null) {
                    myTask = new MyTask();
                    myTask.execute();
                } else {
                    Toast.makeText(MainActivity.this, "이미 동작하고 있습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myTask != null){
                    myTask.cancel(true);
                    myTask = null;
                }
            }
        });

    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        protected Void doInBackground(Void... argu) {
            while(isCancelled() == false) {
                value++;
                if(value <= 1000) {
                    publishProgress();
                }
                else {
                    break;
                }
                try{
                    Thread.sleep(1000);
                } catch(Exception e) {}
            }
            return null;
        }
        protected void onPreExecute() {
            value = 0;
            pb1.setVisibility(View.INVISIBLE);
        }
        protected void onPostExecute(Void result) {
            tv1.setText("1000번을 카운트하였습니다.");
            pb1.setVisibility(View.INVISIBLE);
        }

        protected void onProgressUpdate(Void... argu) {
            pb1.setVisibility(View.VISIBLE);
            tv1.setText(Integer.toString(value));
        }
        protected void onCancelled() {
            tv1.setText("사용자에 의해 종료되었습니다.");
            pb1.setVisibility(View.INVISIBLE);
        }
    }
}
