package com.example.student.mymovieapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button btnJoin, btnLogin;
    EditText edId, edPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnJoin = (Button)findViewById(R.id.btnJoin);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        edId = (EditText)findViewById(R.id.edId);
        edPwd = (EditText)findViewById(R.id.edPwd);

        SharedPreferences sharedPref = getSharedPreferences("userdata", Context.MODE_PRIVATE);

        final String userId = sharedPref.getString("id","");
        final String userPwd = sharedPref.getString("pwd","");
        boolean autoLogin = sharedPref.getBoolean("auto",false);

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userId.equals(edId.getText().toString())){
                    if(userPwd.equals(edPwd.getText().toString())){
                        Intent intent = new Intent(LoginActivity.this, MovieListActivity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(getApplication(),"비밀번호가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }else{
                    Toast.makeText(getApplication(),"ID가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });

        if(autoLogin==true){
            Intent intent = new Intent(LoginActivity.this, MovieListActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
