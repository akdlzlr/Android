package com.example.student.sampleparcelable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    TextView tv1;
    Button btn2;
    public static final String KEY_SIMPLE_DATA = "data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        tv1 = (TextView)findViewById(R.id.tv1);
        btn2 = (Button)findViewById(R.id.button2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 인텐트 객체를 만듭니다.
                Intent intent = new Intent();
                intent.putExtra("Name", "mike");

                // 응답을 전달하고 이 액티비티를 종료합니다.
                setResult(RESULT_OK, intent);

                finish();
            }
        });

        //메인 액티비티로부터 전달 받은 인테트를 확인합니다.
        Intent intent = getIntent();
        processIntent(intent);
    }

    private void processIntent(Intent intent){
        if(intent != null){
            // 인텐트 안의 번들 객체를 참조합니다.
            Bundle bundle = intent.getExtras();

            // 번들 객체 안의 SampleData 객체를 참조합니다.
            SampleData data = (SampleData) bundle.getParcelable(KEY_SIMPLE_DATA);

            // 텍스트뷰에 값을 보여줍니다.
            tv1.setText("전달 받은 데이터 \nNumber : " + data.getNumber()
            +"\nMessage : " + data.getMessage());
        }
    }
}
