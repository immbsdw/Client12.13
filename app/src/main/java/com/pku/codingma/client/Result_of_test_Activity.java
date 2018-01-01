package com.pku.codingma.client;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Result_of_test_Activity extends AppCompatActivity {

    private String[] name = new String[]{"第一次","第二次"};
    private String[] grades =new String[]{"60","80"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_of_test_);

        List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
        for(int i=0; i<name.length; i++){
            Map<String,Object> listItem = new HashMap<String,Object>();
            listItem.put("count",name[i]);
            listItem.put("score",grades[i]);
            listItems.add(listItem);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,
                R.layout.list_view_detail,
                new String[]{"count","score"},
                new int[]{ R.id.test_count,R.id.test_grade});
        ListView list = findViewById(R.id.list_view01);
        list.setAdapter(simpleAdapter);



        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }     //这里的代码用于隐藏系统自带的标题栏
    }
}
