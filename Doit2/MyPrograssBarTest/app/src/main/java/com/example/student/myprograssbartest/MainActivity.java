package com.example.student.myprograssbartest;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ProgressBar pb1, pb2;
    Button start, stop;

    MyAsyncTask myAsyncTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pb1 = (ProgressBar)findViewById(R.id.pb1);
        pb2 = (ProgressBar)findViewById(R.id.pb2);
        start = (Button)findViewById(R.id.start);
        stop = (Button)findViewById(R.id.stop);

        pb1.setVisibility(View.INVISIBLE);

        start.setOnClickListener(new MyBtnListener());
        stop.setOnClickListener(new MyBtnListener());
    }

    class MyBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start:
                    if(myAsyncTask==null){
                        myAsyncTask = new MyAsyncTask();
                        myAsyncTask.execute();
                    }
                    break;
                case R.id.stop:
                    if(myAsyncTask!=null){
                        myAsyncTask.cancel(true);
                        myAsyncTask = null;
                    }
                    break;
            }
        }
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {
        int value;

        @Override
        protected Void doInBackground(Void... voids) {
            while(isCancelled() == false) {
                value++;

                SystemClock.sleep(20);

                if(value <= 100) {
                    publishProgress(value);
                } else {
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            value = 0;
            pb2.setMax(100);
            pb2.setProgress(0);
            pb1.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(MainActivity.this, "100% 완료 되었습니다.",
                    Toast.LENGTH_SHORT).show();
            onCancelled();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            pb2.setProgress(values[0]);
            Log.d("my_onProgressUpdate", values[0].toString());
        }

        @Override
        protected void onCancelled() {
            pb2.setProgress(0);
            pb1.setVisibility(View.INVISIBLE);
            myAsyncTask = null;
        }
    }
}
