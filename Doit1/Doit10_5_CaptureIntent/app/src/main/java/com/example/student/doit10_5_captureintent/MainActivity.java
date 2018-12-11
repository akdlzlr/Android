package com.example.student.doit10_5_captureintent;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    File file = null;
    Button btn1;
    ImageView imageView;
    public static final int REQUEST_IMAGE_CAPTURE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, Activity.MODE_PRIVATE);


        btn1 = (Button) findViewById(R.id.button);
        imageView = (ImageView) findViewById(R.id.imageView);


        try {
            file = createFile();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });
    }

    private File createFile() throws IOException {
        String imageFileName = "test.jpg";
        File storageDir = Environment.getExternalStorageDirectory();
        File curFile = new File(storageDir, imageFileName);
        return curFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        Log.i("onActivityResult","실행됨");
        Log.i("onActivityResult",requestCode+"");
        Log.i("onActivityResult",resultCode+"");
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Log.i("onActivityResult","if문 실행됨");
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            if (file != null) {

                Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                imageView.setImageBitmap(bitmap);
            }else {
                Toast.makeText(getApplicationContext(), "사진이 없다",Toast.LENGTH_LONG).show();
            }
        }
    }
}
