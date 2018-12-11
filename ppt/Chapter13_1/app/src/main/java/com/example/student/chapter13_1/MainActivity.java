package com.example.student.chapter13_1;

import android.Manifest;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.SystemClock;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView1;
    Button btn1, btn2, btn3;
    TextView tv1,tv2;
    SeekBar sbMP3;

    ArrayList<String> mp3List;
    String selectedMP3;

    String mp3Path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/";
    MediaPlayer mPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]
                {android.Manifest.permission.WRITE_EXTERNAL_STORAGE},MODE_PRIVATE);

        mp3List = new ArrayList<String>();

        File[] list = new File(mp3Path).listFiles();

        String fileName, extName;

        for (File file : list){
            fileName = file.getName();
            extName = fileName.substring(fileName.length()-3);
            if(extName.equals((String)"mp3")){
                mp3List.add(fileName);
            }
        }

        listView1 = (ListView) findViewById(R.id.listView1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice,mp3List);
        listView1.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView1.setAdapter(adapter);
        listView1.setItemChecked(0, true);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedMP3 = mp3List.get(i);
            }
        });

        selectedMP3 = mp3List.get(0);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        tv1 = (TextView)findViewById(R.id.tv1);
        tv2 = (TextView)findViewById(R.id.tv2);
        sbMP3 = (SeekBar)findViewById(R.id.sbMP3);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    mPlayer = new MediaPlayer();
                    mPlayer.setDataSource(mp3Path + selectedMP3);
                    mPlayer.prepare();
                    mPlayer.start();
                    btn1.setClickable(true);
                    btn2.setClickable(true);
                    tv1.setText("실행 중인 음악 : "+selectedMP3);
                    sbMP3.setVisibility(View.VISIBLE);

                    new Thread(){
                        SimpleDateFormat tf = new SimpleDateFormat("mm:ss");

                        @Override
                        public void run() {
                            if(mPlayer == null) return;
                            sbMP3.setMax(mPlayer.getDuration());

                            while(mPlayer.isPlaying()){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        sbMP3.setProgress(mPlayer.getCurrentPosition());
                                        tv2.setText("재생 시간 : "+
                                        tf.format(mPlayer.getCurrentPosition()));
                                    }
                                });
                                SystemClock.sleep(200);
                            }

                            sbMP3.setProgress(0);
                        }
                    }.start();

                }catch (IOException e){

                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                mPlayer.reset();
                btn1.setClickable(true);
                btn2.setClickable(true);
                tv1.setText("음악 중지");
                btn1.setText("듣기");
                tv2.setText("재생 시간 : ");
                sbMP3.setProgress(0);
                sbMP3.setVisibility(View.INVISIBLE);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayer.stop();
                btn1.setClickable(true);
                btn2.setClickable(true);
                tv1.setText("음악 일시정지");
                btn1.setText("다시듣기");
                sbMP3.setVisibility(View.INVISIBLE);
            }
        });

        btn2.setClickable(true);

        sbMP3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                    mPlayer.seekTo(sbMP3.getProgress());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
