package com.example.student.mynetworking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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
import java.util.HashMap;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;

public class MainActivity extends AppCompatActivity {

    ImageView iv1;
    TextView tv1;
    Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.tv1);
        iv1 = (ImageView) findViewById(R.id.iv1);

        Realm.init(MainActivity.this);
        mRealm = Realm.getDefaultInstance();

        String url = "http://70.12.110.50:3000";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("number", "1");

        MyHttpTask myHttpTask = new MyHttpTask(url, map);
        myHttpTask.execute();

        String url_img = "http://70.12.110.50:3000/files";
        MyImageHttpTask myImageHttpTask = new MyImageHttpTask(url_img, map);
        myImageHttpTask.execute();

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
            iv1.setImageBitmap(bitmap);
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
                final String title = root.getString("title");
                JSONArray director = root.getJSONArray("director");

                final String[] directorArray = new String[director.length()];

                final RealmList<ArrayStrVO> realmdList = new RealmList<ArrayStrVO>();

                for (int i = 0; i < director.length(); i++) {
                    directorArray[i] = director.getString(i);
                    ArrayStrVO item = new ArrayStrVO();
                    item.setStr(director.getString(i));
                    realmdList.add(item);
                }

                JSONArray actor = root.getJSONArray("actor");

                final String[] actorArray = new String[actor.length()];
                final RealmList<ArrayStrVO> realmaList = new RealmList<>();

                for (int i = 0; i < actor.length(); i++) {
                    actorArray[i] = actor.getString(i);
                    ArrayStrVO item = new ArrayStrVO();
                    item.setStr(actor.getString(i));
                    realmaList.add(item);
                }

                JSONArray category = root.getJSONArray("category");

                final String[] categoryArray = new String[category.length()];
                final RealmList<ArrayStrVO> realmcList = new RealmList<>();

                for (int i = 0; i < category.length(); i++) {
                    categoryArray[i] = category.getString(i);
                    ArrayStrVO item = new ArrayStrVO();
                    item.setStr(category.getString(i));
                    realmcList.add(item);
                }

                final String runningTime = root.getString("runningTime");
                final String openDate = root.getString("openDate");

                String result = "제목 : " + title + "\n감독 : " + directorArray[0] + "\n배우 :";

                String result2 = "\n장르 : "
                        + categoryArray[0] + "\n러닝타임 : " + runningTime + "\n개봉일 : " + openDate;

                tv1.setText(result);
                for (int i = 0; i < actor.length(); i++) {
                    tv1.append(" " + actorArray[i]);
                    if (i != actorArray.length - 1) {
                        tv1.append(" /");
                    }
                }
                tv1.append(result2);
                mRealm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        MovieVO movieVO = realm.createObject(MovieVO.class);
                        movieVO.setTitle(title);
                        movieVO.setDirectory(realmdList);
                        movieVO.setActor(realmaList);
                        movieVO.setCategory(realmcList);
                        movieVO.setRunningTime(runningTime);
                        movieVO.setOpenDate(openDate);
                    }
                });
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
