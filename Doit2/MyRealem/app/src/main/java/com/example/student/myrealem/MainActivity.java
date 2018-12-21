package com.example.student.myrealem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    TextView tv1;
    EditText et1, et2;
    Button btnAdd, btnSearch, btnChange, btnDelete, btnDeleteAll;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView)findViewById(R.id.textView);
        et1 = (EditText)findViewById(R.id.editText);
        et2 = (EditText)findViewById(R.id.editText2);
        btnAdd = (Button)findViewById(R.id.button);
        btnSearch = (Button)findViewById(R.id.button2);
        btnChange = (Button)findViewById(R.id.button3);
        btnDelete = (Button)findViewById(R.id.button4);
        btnDeleteAll = (Button)findViewById(R.id.button5);

        Realm.init(MainActivity.this);
        mRealm = Realm.getDefaultInstance();

        MyBtnListener btnListener = new MyBtnListener();

        btnAdd.setOnClickListener(btnListener);
        btnSearch.setOnClickListener(btnListener);
        btnChange.setOnClickListener(btnListener);
        btnDelete.setOnClickListener(btnListener);
        btnDeleteAll.setOnClickListener(btnListener);

    }

    class MyBtnListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            final String title = et2.getText().toString();
            final String num = et1.getText().toString();

            switch (v.getId()){
                case R.id.button:
                    if(!title.equals("") && !num.equals("")) {
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                MovieVO movieVO = realm.createObject(MovieVO.class);
                                movieVO.setTitle(title);
                                movieVO.setNum(Integer.parseInt(num));
                            }
                        });
                    }
                    break;
                case R.id.button2:
                    RealmResults<MovieVO> results = mRealm.where(MovieVO.class).findAll();
                    String str = "";

                    if(results.size()>0){
                        for(int i = 0; i < results.size();i++){
                            str += ("num : " + results.get(i).getNum()+ " title : "
                            + results.get(i).getTitle()+"\n");
                        }
                    } else {
                        str += "no data";
                    }
                    tv1.setText(str);
                    break;
                case R.id.button3:
                    if(!title.equals("") && !num.equals("")) {
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                MovieVO target = mRealm.where(MovieVO.class)
                                        .equalTo("num", Integer.valueOf(num)).findFirst();
                                target.setTitle(title);
                            }
                        });
                    }
                    break;
                case R.id.button4:
                    if(!num.equals("")) {
                        mRealm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                MovieVO target = mRealm.where(MovieVO.class)
                                        .equalTo("num", Integer.valueOf(num)).findFirst();
                                target.deleteFromRealm();
                            }
                        });
                    }
                    break;
                case R.id.button5:
                    mRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            mRealm.delete(MovieVO.class);
                        }
                    });
                    break;
            }
        }
    }
}
