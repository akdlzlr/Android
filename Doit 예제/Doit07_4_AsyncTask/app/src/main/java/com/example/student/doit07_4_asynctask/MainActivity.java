package com.example.student.doit07_4_asynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar bar;
    BackgroundTask task;
    int value;
    Button btn1, btn2;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bar = (ProgressBar)findViewById(R.id.progress);
        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        tv1 = (TextView)findViewById(R.id.tv1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = new BackgroundTask();
                task.execute(100);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task.cancel(true);
            }
        });
    }

    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{
        protected void onPreExecute(){
            value = 0;
            bar.setProgress(value);
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            while(isCancelled()==false){
                value++;
                if(value>=100){
                    break;
                }else{
                    publishProgress(value);
                }

                try{
                    Thread.sleep(100);
                }catch (InterruptedException es){}

            }
            return value;
        }


        @Override
        protected void onPostExecute(Integer integer) {
            bar.setProgress(0);
            tv1.setText("finished.");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            bar.setProgress(values[0].intValue());
            tv1.setText("Current Value : "+ values[0].toString());
        }

        @Override
        protected void onCancelled(Integer integer) {
            bar.setProgress(0);
            tv1.setText("Cancelled.");
        }
    }
}
