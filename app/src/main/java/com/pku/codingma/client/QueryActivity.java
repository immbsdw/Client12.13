package com.pku.codingma.client;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class QueryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);








        Button searchButton = findViewById(R.id.button_result);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(QueryActivity.this,Result_of_test_Activity.class);
                startActivity(intent);
            }
        });




        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }     //这里的代码用于隐藏系统自带的标题栏
    }
}
