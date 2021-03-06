package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView responseText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendRequest = (Button) findViewById(R.id.send_request);
        responseText = (TextView) findViewById(R.id.response_text);
        sendRequest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.send_request){
            sendRequestWithHttpOkHttp();

        }
    }

    private void sendRequestWithHttpOkHttp() {
        //开启新线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                 try {

                     OkHttpClient client=new OkHttpClient();
                     Request request=new Request.Builder()
                             .url("https://weather1.sina.cn/?vt=4")
                             .build();
                     Response response =client.newCall(request).execute();
                     String responseData=response.body().string();
                     showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    private  void showResponse(final String response){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseText.setText(response);
            }
        });
    }
}
