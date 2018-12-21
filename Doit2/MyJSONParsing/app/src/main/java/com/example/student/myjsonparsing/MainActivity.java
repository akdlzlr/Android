package com.example.student.myjsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);

        String json =
                "{" +
                        "\"user\": \"gildong\"," +
                        "\"color\": [\"red\", \"green\", \"blue\"]" +
                        "}";

        String json2 =
                "{" +
                        "\"weather\": [{\"id\": 721," +
                        "\"main\": \"Haze\"," +
                        "\"description\": \"haze\"," +
                        "\"icon\": \"50n\"" +
                        "}]," +
                        "\"main\": {" +
                        "\"temp\" : 10.14," +
                        "\"pressure\" : 1020," +
                        "\"humidity\" : 37," +
                        "\"temp_min\" : 6," +
                        "\"temp_max\" : 13" +
                        "}," +
                        "\"id\" : 18392," +
                        "\"name\" : \"Seoul\"," +
                        "\"cod\" : 200" +
                        "}";


        try {

            JSONObject root = new JSONObject(json);

            String user_name = root.getString("user");
            JSONArray colors = root.getJSONArray("color");

            String first = colors.getString(0);
            String second = colors.getString(1);
            String third = colors.getString(2);

            for (int i = 0; i < colors.length(); i++) {
                Log.d("show colors", colors.getString(i));
            }

            String result = "user : " + user_name + "\ncolor1 : " + first +
                    "\ncolor2 : " + second + "\ncolor3 : " + third;
            tv1.setText(result);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {

            JSONObject root2 = new JSONObject(json2);

            // weather 배열
            JSONArray weather = root2.getJSONArray("weather");

            // weather 배열안의 객체
            JSONObject weatherObj = weather.getJSONObject(0);

            // 객체 안에 있는 값
            int intId = weatherObj.getInt("id");
            String strMain = weatherObj.getString("main");
            String strDescription = weatherObj.getString("description");
            String strIcon = weatherObj.getString("icon");

            // main 객체
            JSONObject main = root2.getJSONObject("main");

            // 객체 안에 있는 값 꺼내기
            double temp = main.getDouble("temp");
            int pressure = main.getInt("pressure");
            int humidity = main.getInt("humidity");
            int temp_min = main.getInt("temp_min");
            int temp_max = main.getInt("temp_max");

            // id, name, cod 값 꺼내기
            int id = root2.getInt("id");
            String name = root2.getString("name");
            int cod = root2.getInt("cod");

            // 문자열 만들기
            String result = "weather id : " + intId + "\nweather main : " + strMain
                    + "\nweather descripton : " + strDescription +
                    "\nweather icon : " + strIcon + "\nmain temp : " + temp +
                    "\nmain pressure : " + pressure + "\nmain humidity : " + humidity +
                    "\nmain temp_min :  " + temp_min + "\nmain temp_max :  " + temp_max +
                    "\nid : " + id + "\nname : " + name + "\ncod : " + cod;

            // 출력
            tv2.setText(result);
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }
}
