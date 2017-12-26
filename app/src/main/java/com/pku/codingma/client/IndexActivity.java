package com.pku.codingma.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IndexActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
    }
    public void toLogin(View v){
        Intent intent=new Intent(this, MainActivity.class);
        //startActivity(intent);
    }
    public void toPersonal(View v){
        //Intent intent=new Intent(this,PersonalActivity.class);//判断是否登录
        //startActivity(intent);
    }
    public void toSignin(View v){
        //Intent intent=new Intent(this, SigninActivity.class);//判断是否登录
        //startActivity(intent);
    }
    public void toQuiz(View v){
        //Intent intent=new Intent(this, QuizActivity.class);//判断是否登录
        //startActivity(intent);
    }
    public void toQuestion(View v){
        //Intent intent=new Intent(this, QuestionActivity.class);//判断是否登录
        //startActivity(intent);
    }
    public void toDiscuss(View v){
        //Intent intent=new Intent(this, DiscussActivity.class);//判断是否登录
       // startActivity(intent);
    }
    public void toFileman(View v){
        //Intent intent=new Intent(this, FilemanActivity.class);//判断是否登录
        //startActivity(intent);
    }
}

