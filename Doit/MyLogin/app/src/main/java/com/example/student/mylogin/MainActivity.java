package com.example.student.mylogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button button;
    Intent intent;
    EditText editText, editText2;

    final int REQUEST_CONE_SECOND = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.equals(null)||editText2.equals(null)){
                    Toast.makeText(getApplication(), "아이디와 비밀번호를 확인하세요.",Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    intent = new Intent(MainActivity.this, MainMenu.class);
                    startActivityForResult(intent, REQUEST_CONE_SECOND);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            String msg = data.getStringExtra("msg");
            Toast.makeText(getApplicationContext(),msg+" 조회",Toast.LENGTH_SHORT).show();
        }
    }
}
