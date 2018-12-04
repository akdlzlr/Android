package com.example.student.doit07_2_delayed;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tv1;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        btn1 = (Button) findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                request();
            }
        });
    }

    private void request() {
        String title = "원격 요청";
        String msg = "데이터를 요청하시겠습니까?";
        String titleButtonYes = "Yes";
        String titleButtonNo = "No";

        AlertDialog dialog = makeRequestDialog(title, msg, titleButtonYes, titleButtonNo);
        dialog.show();

        tv1.setText("원격 데이터 요청 중 ...");

    }

    private AlertDialog makeRequestDialog(CharSequence title, CharSequence msg,
                          CharSequence titleButtonYes, CharSequence titleButtonNo) {

        final AlertDialog.Builder requestDialog = new AlertDialog.Builder(this);
        requestDialog.setTitle(title);
        requestDialog.setMessage(msg);
        requestDialog.setPositiveButton(titleButtonYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for (int k = 0; k < 10; k++) {
                    try {
                        Thread.sleep(1000);
                    }catch (Exception ex){

                    }
                    tv1.setText("원격 데이터 요청 완료.");
                }
            }
        });

        requestDialog.setNegativeButton(titleButtonNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tv1.setText("네트워크 지연 시간 시뮬");
            }
        });

        return requestDialog.show();

    }

}
