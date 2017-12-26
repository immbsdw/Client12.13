package com.pku.codingma.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static User theUser=new User("a","a",-1,"a",1);
    private EditText usrIdEditText;
    private EditText ursPasswordEditText;
    private EditText ursTypeEditText;
    private Button mLoginButtonEditText;
    private String originAddress = "http://10.0.0.3:8080/matsAdmin/api/usr/usrLogin.do";

    Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = "";

            if ("OK".equals(msg.obj.toString())){
                result = "success";
            }else if ("Wrong".equals(msg.obj.toString())){
                result = "fail";
            }else {
                result = msg.obj.toString();
            }
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        usrIdEditText= (EditText) findViewById(R.id.usrId);
        ursPasswordEditText = (EditText) findViewById(R.id.ursPassword);
        ursTypeEditText = (EditText) findViewById(R.id.ursType);
        mLoginButtonEditText = (Button) findViewById(R.id.loginButton);
    }

    private void initEvent() {
        mLoginButtonEditText.setOnClickListener(this);
    }

    public void login() {
        //取得用户输入的账号和密码
        if (!isInputValid()){
            return;
        }
        int type=Integer.parseInt(ursTypeEditText.getText().toString());
        User params=new User(usrIdEditText.getText().toString(),ursPasswordEditText.getText().toString(),1,"name1",type);
        try {
            String compeletedURL = HttpUtil.getURLWithParams(originAddress, params);
             HttpUtil.sendHttpRequest(compeletedURL,theUser,new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.obj = response;
                    mHandler.sendMessage(message);
                }

                @Override
                public void onError(Exception e) {
                    Message message = new Message();
                    message.obj = e.toString();
                    mHandler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isInputValid() {
        //检查用户输入的合法性，这里暂且默认用户输入合法
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginButton:
                login();
                break;
        }
        //if(theUser.getUsrSex()!=-1) {
            Intent intent = new Intent(MainActivity.this, IndexActivity.class);
            startActivity(intent);
        //}
    }
}
