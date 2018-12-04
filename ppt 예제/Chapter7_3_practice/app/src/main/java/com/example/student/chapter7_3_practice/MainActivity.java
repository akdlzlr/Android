package com.example.student.chapter7_3_practice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText tvName, tvEmail;
    Button btn1;
    EditText etName, etEmail;
    TextView toastText1;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        tvName = (EditText) findViewById(R.id.tvName);
        tvEmail = (EditText) findViewById(R.id.tvEmail);
        btn1 = (Button)findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogView = (View) View.inflate(MainActivity.this,
                        R.layout.dialog1, null);
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);


                // diologView의 findViewById로 찾아야한다.
                etName = (EditText) dialogView.findViewById(R.id.etName);
                etEmail = (EditText) dialogView.findViewById(R.id.etEmail);


                dlg.setTitle("사용자 정보 입력");
                etName.setText(tvName.getText().toString());
                etEmail.setText(tvEmail.getText().toString());
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        tvName.setText(etName.getText().toString());
                        tvEmail.setText(etEmail.getText().toString());
                    }
                });
                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this,
                                R.layout.toast1, null);
                        toastText1 = (TextView) toastView.findViewById(R.id.toastText1);
                        toastText1.setText("취소했습니다.");

                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int xOffset = (int) (Math.random()*display.getWidth());
                        int yOffset = (int) (Math.random()*display.getHeight());
                        toast.setGravity(Gravity.TOP|Gravity.LEFT,xOffset,yOffset);
                        toast.setView(toastView);
                        toast.show();
                    }
                });
                dlg.show();
            }
        });





    }
}
