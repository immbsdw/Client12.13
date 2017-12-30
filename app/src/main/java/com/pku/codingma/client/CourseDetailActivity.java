package com.pku.codingma.client;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseDetailActivity extends AppCompatActivity {

    private ImageView avatar;
    private TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
    }
    @Override
    protected void onStart(){
        avatar=(ImageView) findViewById(R.id.avatar);
        username=(TextView) findViewById(R.id.username);
        //avatar.setImageResource(R.drawable.sex);
        username.setText(StaticUsr.userName);
        super.onStart();
    }
    public void toQuery(View v){
        //Intent intent=new Intent(this,QueryActivity.class);
        //startActivity(intent);
    }
    public void toSignin(View v){
        Intent intent=new Intent(this, SigninActivity.class);
        //startActivity(intent);
    }
    public void toQuiz(View v){
        Intent intent=new Intent(this, QuizActivity.class);
        //startActivity(intent);
    }
    public void toQuestion(View v){
        //Intent intent=new Intent(this, QuestionActivity.class);
        //startActivity(intent);
    }
    public void toDiscuss(View v){
        Intent intent=new Intent(this, DiscussActivity.class);
       // startActivity(intent);
    }
    public void toFileDown(View v){
        //Intent intent=new Intent(this, FileDownActivity.class);
        //startActivity(intent);
    }
}

