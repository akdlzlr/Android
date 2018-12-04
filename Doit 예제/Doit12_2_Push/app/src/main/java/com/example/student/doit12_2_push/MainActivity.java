package com.example.student.doit12_2_push;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button sendBtn;
    TextView logTv, receiveTv;
    EditText sendEt;

    RequestQueue queue;
    String regId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET}, Activity.MODE_PRIVATE);

        sendEt = (EditText) findViewById(R.id.sendEt);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        logTv = (TextView) findViewById(R.id.logTv);
        receiveTv = (TextView) findViewById(R.id.receiveTv);

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String input = sendEt.getText().toString();
                send(input);
            }
        });

        queue = Volley.newRequestQueue(getApplicationContext());

        getRegistratinId();
    }

    public void getRegistratinId() {
        println("getRegistrationId() 호출 됨");

        regId = FirebaseInstanceId.getInstance().getToken();
        println("regID : " + regId);
    }

    public void println(String data) {
        logTv.append(data + "\n");
    }

    public void send(String input) {
        JSONObject requestData = new JSONObject();

        try {
            requestData.put("priority", "high");
            JSONObject dataObj = new JSONObject();
            dataObj.put("contents", input);
            requestData.put("data", dataObj);

            JSONArray idArray = new JSONArray();
            idArray.put(0, regId);
            requestData.put("registration_ids", idArray);
        } catch (Exception e) {
            e.printStackTrace();
        }

        sendData(requestData, new SendResponseListener() {
            @Override
            public void onRequestStarted() {
                println("onRequestStarted() 호출됨");
            }

            @Override
            public void onRequestCompleted() {
                println("onRequestCompleted() 호출됨");
            }

            @Override
            public void onRequestWithError(VolleyError error) {
                println("onRequestWithError() 호출됨");
            }
        });
    }

    public interface SendResponseListener {
        public void onRequestStarted();

        public void onRequestCompleted();

        public void onRequestWithError(VolleyError error);
    }

    public void sendData(JSONObject requestData, final SendResponseListener listener) {
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://fcm.googleapis.com/fcm/send",
                requestData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        listener.onRequestCompleted();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onRequestWithError(error);
            }
        }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String,String>();
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<String,String>();
                header.put("Authorization","key=AAAAJmZgkDY:APA91bF1ym_jqhvlQt85mtsmHf8pIc4ZVw6o0tiQzbCr9a43ZsWSYCPCe_Hctsa4ifUZMmTVYf8rYPcnR6aOCaiJ0Mno6CmxvA3uJ6XRm-RHtKnqGgV5kpNNlS6CzUctq0nOkTPiSIn6");
                return header;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        request.setShouldCache(false);
        listener.onRequestStarted();
        queue.add(request);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        println("onNewIntent() called.");

        if(intent != null){
            processIntent(intent);
        }

        super.onNewIntent(intent);
    }

    private void processIntent(Intent intent){
        String from = intent.getStringExtra("from");
        if(from==null){
            println("from is null");
            return;
        }

        String contents = intent.getStringExtra("contents");

        println("DATA : "+from+", "+ contents);
        receiveTv.setText("받는 단말\n["+from+"]로부터 수신한 데이터 : "+ contents);
    }
}
