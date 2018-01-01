package com.pku.codingma.client;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity  extends AppCompatActivity implements View.OnClickListener{
    private EditText usrIDEditText;
    private EditText usrNameEditText;
    private EditText usrPassword1EditText;
    private EditText usrPassword2EditText;
    private CheckBox check_boy=null;
    private CheckBox check_girl=null;
    private Button mbutton_register;
    private int sextype=-1;
    private String originAddress = "http://10.0.0.6:8080/matsAdmin/api/usr/registerUsr.do";
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
            Toast.makeText(RegisterActivity.this, result, Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initEvent();
    }

    private void initView() {
        usrIDEditText= (EditText) findViewById(R.id.usrIDEditText);
        usrNameEditText = (EditText) findViewById(R.id.usrNameEditText);
        usrPassword1EditText = (EditText) findViewById(R.id.usrPassword1EditText);
        usrPassword2EditText = (EditText) findViewById(R.id.usrPassword2EditText);
        mbutton_register = (Button) findViewById(R.id.button_register);
        check_boy=(CheckBox)findViewById(R.id.check_boy);
        check_girl=(CheckBox)findViewById(R.id.check_girl);
        check_boy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    sextype=0;
                }else{
                    sextype=-1;
                }
            }
        });
        check_girl.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked){
                    sextype=1;
                }else{
                    sextype=-1;
                }
            }
        });
    }

    private void initEvent() {
        mbutton_register.setOnClickListener(this);
    }

    public void register() {
        //取得用户输入的账号和密码
        System.out.println("in register");
        if (!isInputValid()){
            return;
        }
        User params=new User(usrIDEditText.getText().toString(),usrPassword1EditText.getText().toString(),sextype,usrNameEditText.getText().toString(),0);
        try {
            String compeletedURL = HttpUtil.getURLWithParams(originAddress, params);
            HttpUtil.sendHttpRequest(compeletedURL,new HttpCallbackListener() {
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

    public void onClick (View v) {
        switch (v.getId()){
            case R.id.button_register:
                register();
                Intent intent1 = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent1);
                break;
        }
    }
    }

