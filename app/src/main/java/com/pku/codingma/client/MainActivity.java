package com.pku.codingma.client;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyApplication application;
    private EditText usrIdEditText;
    private EditText ursPasswordEditText;
    private CheckBox ursType0=null;
    private CheckBox ursType1=null;
    private Button mLoginButtonEditText;
    private Button mRegisterButtonEditText;
    JsonObject returnData2;
    JsonObject returnData1;
    private String originAddress = "http://10.0.0.6:8080/matsAdmin/api/usr/usrLogin.do";
    int   usrtype=-1;
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
        usrIdEditText= (EditText) findViewById(R.id.usrId);
        ursPasswordEditText = (EditText) findViewById(R.id.ursPassword);
        mLoginButtonEditText = (Button) findViewById(R.id.loginButton);
        mRegisterButtonEditText = (Button) findViewById(R.id.registerButton);
        ursType0=(CheckBox)findViewById(R.id.ursType0);
        ursType1=(CheckBox)findViewById(R.id.ursType1);
        ursType0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                   usrtype=0;
                }else{
                    usrtype=-1;
                }
            }
        });
        ursType1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    usrtype=1;
                }else{
                    usrtype=-1;
                }
            }
        });
    }

    private void initEvent() {
        mLoginButtonEditText.setOnClickListener(this);
        mRegisterButtonEditText.setOnClickListener(this);
    }

    public void login() {
        //取得用户输入的账号和密码
        if (!isInputValid()){
            return;
        }
        User params=new User(usrIdEditText.getText().toString(),ursPasswordEditText.getText().toString(),1,"name1",usrtype);
        try {
            String compeletedURL = HttpUtil.getURLWithParams(originAddress,params);
            HttpUtil.sendHttpRequest(compeletedURL,new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    try {
                        Message message = new Message();
                        System.out.println(response + " in here");
                        returnData1 = new JsonParser().parse(response).getAsJsonObject();
                        returnData2 = new JsonParser().parse(returnData1.get("data").toString()).getAsJsonObject();
                        application = (MyApplication) getApplication();
                        application.setUsrName(returnData2.get("usrName").toString());
                        application.setUsrPassword(returnData2.get("usrPassword").toString());
                        application.setUsrId(returnData2.get("usrId").toString());
                        application.setUsrType(returnData2.get("usrType").getAsInt());
                        application.setUsrSex(returnData2.get("usrSex").getAsInt());
                        System.out.println(application.getUsrId());
                        System.out.println(application.getUsrPassword());
                        System.out.println(application.getUsrType());
                        System.out.println(application.getUsrSex());
                        System.out.println(application.getUsrName());
                        Intent intent1 = new Intent(MainActivity.this, IndexActivity.class);
                        startActivity(intent1);
                    }
                    catch(Exception e)
                    {
                        Message message = new Message();
                        message = new Message();
                        message.obj = "密码或用户类型错误";
                        mHandler.sendMessage(message);
                    }
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
            case R.id.registerButton:
                Intent intent2 = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent2);
        }
    }
}
