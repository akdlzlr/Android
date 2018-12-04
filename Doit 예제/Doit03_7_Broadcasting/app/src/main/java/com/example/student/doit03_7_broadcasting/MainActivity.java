package com.example.student.doit03_7_broadcasting;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if(permissionCheck== PackageManager.PERMISSION_GRANTED){
            Toast.makeText(getApplicationContext(),"SMS 수신 권한 있음", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"SMS 수신 권한 없음", Toast.LENGTH_SHORT).show();
            if(ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.RECEIVE_SMS
            )){
                Toast.makeText(this, "SMS 권한 설명 필요함.",Toast.LENGTH_SHORT).show();
            }else{
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.RECEIVE_SMS},1);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1: {
                if(grantResults.length>0&& grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getApplicationContext(),"SMS 권한을 사용자가 승인함",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"SMS 권한을 사용자가 거부함",Toast.LENGTH_SHORT).show();
                }
            }
        }
        return;
    }
}
