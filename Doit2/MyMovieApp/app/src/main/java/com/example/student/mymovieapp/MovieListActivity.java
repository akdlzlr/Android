package com.example.student.mymovieapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.student.mymovieapp.format.ListViewAdapter;
import com.example.student.mymovieapp.format.Movie;
import com.example.student.mymovieapp.format.MovieListVO;
import com.example.student.mymovieapp.format.MovieVO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import io.realm.Realm;

public class MovieListActivity extends AppCompatActivity {


    Button btnSetting;
    ListView list;
    ArrayList<Movie> arrayList = new ArrayList<Movie>();
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(MovieListActivity.this);
        mRealm = Realm.getDefaultInstance();

        btnSetting = (Button)findViewById(R.id.btnSetting);

        // 2. 리스트뷰 객체 만들기
        list = (ListView) findViewById(R.id.ListView);

        String url = "http://70.12.110.55:3000";
        String url_img = "http://70.12.110.55:3000/files";


        for(int i=0; i<6;i++){
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("number", ""+(i+1));

            MyHttpTask myHttpTask = new MyHttpTask(url, map);
            myHttpTask.execute();

            MyImageHttpTask myImageHttpTask = new MyImageHttpTask(url_img, map);
            myImageHttpTask.execute();
        }

        // 4. 리스트 뷰에 onItemClickListener 등록하기
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Hi~", Toast.LENGTH_SHORT).show();
            }
        });

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieListActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    class MyImageHttpTask extends AsyncTask<Void, Void, Bitmap> {

        String url_str;
        HashMap<String, String> map;

        public MyImageHttpTask(String url_str, HashMap<String, String> map) {
            super();

            this.url_str = url_str;
            this.map = map;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap result = null;
            String post_query = "";
            PrintWriter printWriter = null;

            try {
                URL text = new URL(url_str);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(100000);
                http.setReadTimeout(100000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if (map != null && map.size() > 0) {
                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while (keys.hasNext()) {

                        if (!first_query_part) {
                            post_query += "&";
                        }
                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));
                        first_query_part = false;
                        printWriter = new PrintWriter(new OutputStreamWriter(
                                http.getOutputStream(), "UTF-8"));
                        printWriter.write(post_query);
                        printWriter.flush();

                        // receive from server
                        result = BitmapFactory.decodeStream(http.getInputStream());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            } finally {
                try {
                    if (printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return result;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            int ix = Integer.parseInt(map.get("number"))-1;
            Movie movieImg = arrayList.get(ix);
            movieImg.setBitmap(bitmap);
            ListViewAdapter adapter = new ListViewAdapter(MovieListActivity.this,
                    R.layout.movie_item, arrayList);
            list.setAdapter(adapter);
            this.cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    class MyHttpTask extends AsyncTask<Void, Void, String> {

        String url_str;
        HashMap<String, String> map;

        public MyHttpTask(String url_str, HashMap<String, String> map) {
            super();

            this.url_str = url_str;
            this.map = map;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String result = null;
            String post_query = "";
            PrintWriter printWriter = null;
            BufferedReader bufferedReader = null;

            try {
                URL text = new URL(url_str);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if (map != null && map.size() > 0) {

                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while (keys.hasNext()) {

                        if (!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    // sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(
                            http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    // receive from server
                    bufferedReader = new BufferedReader(new InputStreamReader(
                            http.getInputStream(), "UTF-8"));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }

                    result = stringBuffer.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (bufferedReader != null) bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            // do something

            String json = s;

            try {
                JSONObject root = new JSONObject(json);
                String title = root.getString("title");
                JSONArray director = root.getJSONArray("director");
                String[] directorArray= new String[director.length()];

                for(int i=0; i<director.length();i++){
                    directorArray[i]=director.getString(i);
                }
                String director1 = director.getString(0);

                JSONArray actor = root.getJSONArray("actor");

                String[] actorArray= new String[actor.length()];

                for(int i=0; i<actor.length();i++){
                    actorArray[i]=actor.getString(i);
                }

                JSONArray category = root.getJSONArray("category");

                String[] categoryArray= new String[category.length()];

                for(int i=0; i<category.length();i++){
                    categoryArray[i]=category.getString(i);
                }

                String runningTime = root.getString("runningTime");
                String openDate = root.getString("openDate");

                Movie item = new Movie(
                        title, directorArray, actorArray,categoryArray,runningTime, openDate);
                Log.d("Movie", item.toString());
                arrayList.add(item);

                // 3. 리스트뷰에 adapter 등록하기
            /*  첫번째 매개변수 : 액티비티 정보(context 객체)
                두번째 매개변수 : 리스트뷰 항목의 레이아웃(안드로이드 제공)
                세번째 매개변수 : 표시할 데이터들 */
                ListViewAdapter adapter = new ListViewAdapter(MovieListActivity.this,
                        R.layout.movie_item, arrayList);
                list.setAdapter(adapter);


            } catch (JSONException e) {
                e.printStackTrace();
            }


            this.cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}