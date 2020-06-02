package com.riskm.androidclient.ui.login;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.se.omapi.Session;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.alibaba.fastjson.JSON;
import com.riskm.androidclient.R;
import com.riskm.androidclient.ui.admin.AdminActivity;
import com.riskm.androidclient.util.CallBackUtil;
import com.riskm.androidclient.util.RealResponse;
import com.riskm.androidclient.util.SessionRecord;
import com.riskm.androidclient.util.UrlHttpUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    private final String URL_Index = "http://10.0.3.2:8080/";
    private EditText psw;
    private EditText account;
    private Button submit;
    private EditText captche;
    private ImageView captcheImg;
    private UrlHttpUtil urlHttpUtil;
    private RealResponse realResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        urlHttpUtil = new UrlHttpUtil();
        UrlHttpUtil.post(URL_Index, null, new CallBackUtil.CallBackDefault() {
            @Override
            public void onFailure(int code, String errorMessage) {
                System.out.print(errorMessage);
            }

            @Override
            public void onResponse(RealResponse response) {
                realResponse = response;
                String cookieTmp = SessionRecord.getCookieID(realResponse);
                SessionRecord.setCookie(cookieTmp);
                initFrom();
                imgCaptcheUpdate();
            }
        });

    }

    private void initFrom() {
        psw = findViewById(R.id.loginPassword);
        account = findViewById(R.id.loginAccount);
        submit = findViewById(R.id.submit);
        captcheImg = findViewById(R.id.captcheImg);
        captcheImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgCaptcheUpdate();
            }
        });
        captche = findViewById(R.id.captche);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginRequest();
            }
        });
    }



    private void imgCaptcheUpdate() {
        Random random = new Random();
        String urlCapche = "http://10.0.3.2:8080/login/getCaptcheCodeImg";
        Map<String, String> mapDat = new HashMap<String, String>();
        mapDat.put("t", Integer.toString(random.nextInt()));
        Map<String, String> headMap = new HashMap<>();
        //String cookieTmp = SessionRecord.getCookieID(realResponse);
        //SessionRecord.setCookie(cookieTmp);
        headMap.put("Cookie", SessionRecord.getCookie());
        UrlHttpUtil.post(urlCapche, null, headMap, new CallBackUtil.CallBackBitmap() {
            @Override
            public void onFailure(int code, String errorMessage) {
                System.out.print(errorMessage);
                //
            }

            @Override
            public void onResponse(Bitmap response) {
                captcheImg.setImageBitmap(response);
            }
        });
    }

    private void loginRequest() {
        Map<String, String> mapDat = new HashMap<String, String>();
        mapDat.put("password", psw.getText().toString());
        mapDat.put("account", account.getText().toString());
        mapDat.put("captche", captche.getText().toString());
        Map<String, String> headMap = new HashMap<>();
        //String cookieTmp = SessionRecord.getCookieID(realResponse);
        //SessionRecord.setCookie(cookieTmp);
        headMap.put("Cookie", SessionRecord.getCookie());
        UrlHttpUtil.post("http://10.0.3.2:8080/login/login", mapDat, headMap, new CallBackUtil.CallBackDefault() {
            @Override
            public void onFailure(int code, String errorMessage) {
                System.out.print(errorMessage);
            }

            @Override
            public void onResponse(RealResponse response) {
                imgCaptcheUpdate();
                BufferedReader reader;

                StringBuffer sbf = new StringBuffer();
                realResponse = response;
                try {
                    String strRead = null;
                    reader = new BufferedReader(new InputStreamReader(realResponse.inputStream, StandardCharsets.UTF_8));
                    while ((strRead = reader.readLine()) != null) {
                        sbf.append(strRead);
                        sbf.append("\r\n");
                    }
                    reader.close();
                    Map map = JSON.parseObject(sbf.toString());
                    if ("success".equals(map.get("state"))) {
                        if ("admin".equals(map.get("msg"))) {
                            System.out.println("admin success");
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            startActivity(intent);
                        } else if ("student".equals(map.get("msg"))) {
                            System.out.println("student success");
                        } else {
                            System.out.println("error");
                        }
                    }else{
                        //TODO if login failed and then give some notice
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private String convertStreamToString(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }

        String reponse = sb.toString();
        return reponse;
    }
}
