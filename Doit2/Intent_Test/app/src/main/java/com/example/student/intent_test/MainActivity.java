package com.example.student.intent_test;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    Button web, phone, map, contacts;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        web = (Button)findViewById(R.id.button);
        phone = (Button)findViewById(R.id.button2);
        map = (Button)findViewById(R.id.button3);
        contacts = (Button)findViewById(R.id.button4);
        editText = (EditText)findViewById(R.id.editText);

        IntentClickListener intentClickListener = new IntentClickListener();

        web.setOnClickListener(intentClickListener);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editText.getText().toString();

                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
        map.setOnClickListener(intentClickListener);
        contacts.setOnClickListener(intentClickListener);
    }

   class IntentClickListener implements View.OnClickListener{

        Intent intent = null;

       @Override
       public void onClick(View v) {
           switch (v.getId()){
               case R.id.button:
                   intent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("http://www.naver.com"));
                   break;
               case R.id.button2:
                   intent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("tel:01012345678"));
                   break;
               case R.id.button3:
                   intent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("geo:36.6349120,127.4869820"));
                   break;
               case R.id.button4:
                   intent = new Intent(Intent.ACTION_VIEW,
                           Uri.parse("content://contacts/people/"));
                   break;
           }
           if(intent != null){
               startActivity(intent);
           }
       }
   }
}
