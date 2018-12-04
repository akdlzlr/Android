package com.example.student.chapter6_7_webviewex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button prev, move;
    EditText editUri;
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUri = (EditText)findViewById(R.id.editUri);
        prev = (Button)findViewById(R.id.prev);
        move = (Button)findViewById(R.id.move);
        webView1 = (WebView)findViewById(R.id.webView1);

        webView1.setWebViewClient(new IoTWebViewClient());

        WebSettings webSettings = webView1.getSettings();
        webSettings.setBuiltInZoomControls(true);

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView1.loadUrl(editUri.getText().toString());
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView1.goBack();
            }
        });
    }

    class IoTWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
    }
}
