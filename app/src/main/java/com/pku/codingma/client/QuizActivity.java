package com.pku.codingma.client;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pku.codingma.client.model.Answer;
import com.pku.codingma.client.model.Page;
import com.pku.codingma.client.model.Quesition;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        xInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 数据
        initDate();
        // 提交按钮
        TextView button = findViewById(R.id.tv_commit);
        button.setOnClickListener(new submitOnClickListener(page));
    }

    private String resultJosn = "{\"result\":\"1\",\"type\":\"1\",\"data\":[{\"type\":\"1\",\"eid\":\"11\",\"problem\":\"1、你现在上的是什么课？\",\"trueanswer\":\"B\",\"optionData\":[{\"option\":\"高等数学\",\"isChecked\":\"0\"},{\"option\":\"软件项目管理\",\"isChecked\":\"0\"},{\"option\":\"C++程序设计\",\"isChecked\":\"0\"},{\"option\":\"大学物理\",\"isChecked\":\"0\"}]},{\"type\":\"1\",\"eid\":\"12\",\"problem\":\"2、你认为你这门课的学习怎么样？\",\"trueanswer\":\"C\",\"optionData\":[{\"option\":\"好\",\"isChecked\":\"0\"},{\"option\":\"一般\",\"isChecked\":\"0\"},{\"option\":\"很好\",\"isChecked\":\"0\"},{\"option\":\"差\",\"isChecked\":\"0\"}]},{\"type\":\"1\",\"eid\":\"13\",\"problem\":\"3、1+1=2？ \",\"trueanswer\":\"A\",\"optionData\":[{\"option\":\"正确\",\"isChecked\":\"0\"},{\"option\":\"错误\",\"isChecked\":\"0\"}]},{\"type\":\"1\",\"eid\":\"14\",\"problem\":\"4、C++和JAVA都是编程语言吗？\",\"trueanswer\":\"B\",\"optionData\":[{\"option\":\"正确\",\"isChecked\":\"0\"},{\"option\":\"错误\",\"isChecked\":\"0\"}]},{\"type\":\"1\",\"eid\":\"15\",\"problem\":\"5、 老师姓什么？\",\"trueanswer\":\"D\",\"optionData\":[{\"option\":\"赵\",\"isChecked\":\"0\"},{\"option\":\"钱\",\"isChecked\":\"0\"},{\"option\":\"孙\",\"isChecked\":\"0\"},{\"option\":\"余\",\"isChecked\":\"0\"}]}]}\n";
    private LinearLayout test_layout;

    // 答案列表
    private ArrayList<Answer> the_answer_list;
    // 问题列表
    private ArrayList<Quesition> the_quesition_list;
    // 问题所在的View
    private View que_view;
    // 答案所在的View
    private View ans_view;
    private LayoutInflater xInflater;
    private Page page;
    // 下面这两个list是为了实现点击的时候改变图片，因为单选多选时情况不一样，为了方便控制
    // 存每个问题下的TextView
    private ArrayList<ArrayList<TextView>> textlist = new ArrayList<>();
    // 存每个答案的TextView
    private ArrayList<TextView> textlist2;



    private void initDate() {
        ArrayList<Quesition> quesitionsList = null;
        try {
            quesitionsList = new ArrayList<>();//问题列表

            JSONObject resultJson = new JSONObject(resultJosn);
            JSONArray arrayJson = resultJson.optJSONArray("data");

            for (int i=0;i<arrayJson.length();i++){
                JSONObject subObject = arrayJson.getJSONObject(i);

                ArrayList<Answer> answers = new ArrayList<>();

                JSONArray arrayAnswerJson = subObject.optJSONArray("optionData");//问题的答案
                for (int j=0; j< arrayAnswerJson.length(); j++) {
                    JSONObject answerObject = arrayAnswerJson.getJSONObject(j);
                    Answer a_answer = new Answer();
                    a_answer.setAnswerId(""+j);
                    a_answer.setAnswer_content(answerObject.getString("option"));
                    a_answer.setAns_state(Integer.parseInt(answerObject.getString("isChecked")));

                    answers.add(a_answer);
                }

                Quesition q_quesition = new Quesition();
                q_quesition.setQuesitionId(subObject.getString("eid"));//问题的id
                q_quesition.setType(subObject.getString("type"));//类型，1单选 2判断 3不定项
                q_quesition.setContent(subObject.getString("problem"));//问题
                q_quesition.setAnswers(answers);
                q_quesition.setQue_state(0);

                quesitionsList.add(q_quesition);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        page = new Page();
        page.setPageId("1");
        page.setStatus("0");
        page.setTitle("题目");
        page.setQuesitions(quesitionsList);
        // 加载布局
        initView(page);
    }

    private void initView(Page page) {
        // TODO Auto-generated method stub
        // 这是要把问题的动态布局加入的布局
        test_layout = (LinearLayout) findViewById(R.id.lly_test);
        TextView page_txt = (TextView) findViewById(R.id.txt_title);
        page_txt.setText(page.getTitle());
        // 获得问题即第二层的数据
        the_quesition_list = page.getQuesitions();
        // 根据第二层问题的多少，来动态加载布局
        for (int i = 0; i < the_quesition_list.size(); i++) {
            que_view = xInflater.inflate(R.layout.quesition_layout, null);
            ImageView iv_type = (ImageView) que_view.findViewById(R.id.iv_type);
            TextView txt_que = (TextView) que_view.findViewById(R.id.txt_question_item);
            // 这是第三层布局要加入的地方
            LinearLayout add_layout = (LinearLayout) que_view.findViewById(R.id.lly_answer);

            View v_line = (View) que_view.findViewById(R.id.v_line);
            if(i == 0){//第一道题目的分隔条不用显示
                v_line.setVisibility(View.GONE);
            }

            //类型：1选择题；2判断题 3不定项
            if (the_quesition_list.get(i).getType().equals("1")) {
                iv_type.setImageResource(R.mipmap.single_menu);
            } else if (the_quesition_list.get(i).getType().equals("2")){
                iv_type.setImageResource(R.mipmap.judge_menu);
            } else if (the_quesition_list.get(i).getType().equals("3")){
                iv_type.setImageResource(R.mipmap.more_menu);
            }

            txt_que.setText(the_quesition_list.get(i).getContent());//设置问题题目
            // 获得答案即第三层数据
            the_answer_list = the_quesition_list.get(i).getAnswers();
            textlist2 = new ArrayList<>();
            for (int j = 0; j < the_answer_list.size(); j++) {

                ans_view = xInflater.inflate(R.layout.answer_layout, null);
                TextView txt_ans = (TextView) ans_view.findViewById(R.id.txt_answer_item);
                TextView tv_menu = (TextView) ans_view.findViewById(R.id.tv_menu);

                //为每个问题的选项加上相应的ABCD...
                if (j==0){
                    tv_menu.setText("A");
                }else if (j==1){
                    tv_menu.setText("B");
                }else if (j==2){
                    tv_menu.setText("C");
                }else if (j==3){
                    tv_menu.setText("D");
                }else if (j==4){
                    tv_menu.setText("E");
                }else if (j==5){
                    tv_menu.setText("F");
                }else if (j==6){
                    tv_menu.setText("G");
                }
                // 判断哪个选项已选
                if (the_answer_list.get(j).getAns_state() == 1) {
//                    Toast.makeText(getApplicationContext(),"1",Toast.LENGTH_SHORT).show();
                    the_quesition_list.get(i).setQue_state(1);
                    tv_menu.setBackgroundResource(R.drawable.shape_red);
                    tv_menu.setTextColor(getResources().getColor(R.color.colorWhite));
                } else {
//                    Toast.makeText(getApplicationContext(),"0",Toast.LENGTH_SHORT).show();
                    tv_menu.setBackgroundResource(R.drawable.shape_white);
                    tv_menu.setTextColor(getResources().getColor(R.color.colorblack));
                }

                textlist2.add(tv_menu);
                txt_ans.setText(the_answer_list.get(j).getAnswer_content());
                LinearLayout lly_answer_size = (LinearLayout) ans_view.findViewById(R.id.lly_answer_size);

                if (j%2!=0){//为了美观了，将选项的背景隔开一下
                    lly_answer_size.setBackgroundColor(getResources().getColor(R.color.colorWhite));
                }
                //设置点击事件
                lly_answer_size .setOnClickListener(new answerItemOnClickListener(i, j, the_answer_list, txt_ans));
                add_layout.addView(ans_view);
            }
            textlist.add(textlist2);

            test_layout.addView(que_view);//将生成的问题都添加到一个布局文件中
        }
    }

    /**
     * 试卷各个选项的点击事件
     */
    class answerItemOnClickListener implements View.OnClickListener {
        private int i;
        private int j;
        private TextView txt;
        private ArrayList<Answer> the_answer_lists;

        public answerItemOnClickListener(int i, int j, ArrayList<Answer> the_answer_list, TextView text) {
            this.i = i;
            this.j = j;
            this.the_answer_lists = the_answer_list;
            this.txt = text;
        }

        // 实现点击选项后改变选中状态以及对应图片
        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            // 判断当前问题是单选还是多选
            if (the_quesition_list.get(i).getType().equals("3")) {//1选择题；2判断题 3不定项

                for(int z=0;z<the_answer_list.size();z++){
                    if(the_answer_list.get(z).getAns_state()==1){
                        the_quesition_list.get(i).setQue_state(1);
//                        return;
                    }else{
                        the_quesition_list.get(i).setQue_state(0);
                    }
                }
                if (the_answer_lists.get(j).getAns_state() == 0) {
//                    Toast.makeText(getApplication(), "0", Toast.LENGTH_SHORT).show();
                    textlist.get(i).get(j).setBackgroundResource(R.drawable.shape_red);
                    textlist.get(i).get(j).setTextColor(getResources().getColor(R.color.colorWhite));
                    the_answer_lists.get(j).setAns_state(1);
                    the_quesition_list.get(i).setQue_state(1);
                } else {
//                    Toast.makeText(getApplication(), "1", Toast.LENGTH_SHORT).show();
                    textlist.get(i).get(j).setBackgroundResource(R.drawable.shape_white);
                    textlist.get(i).get(j).setTextColor(getResources().getColor(R.color.colorblack));
                    the_answer_lists.get(j).setAns_state(0);
                    the_quesition_list.get(i).setQue_state(0);
                }
            } else {// 单选
                for (int z = 0; z < the_answer_lists.size(); z++) {
                    if (z == j) {
//                        Toast.makeText(getApplication(), "3", Toast.LENGTH_SHORT).show();
                        // 如果当前未被选中
                        textlist.get(i).get(j).setBackgroundResource(R.drawable.shape_red);
                        textlist.get(i).get(j).setTextColor(getResources().getColor(R.color.colorWhite));
                        the_answer_lists.get(z).setAns_state(1);
                        the_quesition_list.get(i).setQue_state(1);
                    } else {
//                        Toast.makeText(getApplication(), "4", Toast.LENGTH_SHORT).show();
                        the_answer_lists.get(z).setAns_state(0);
                        the_quesition_list.get(i).setQue_state(1);
                        textlist.get(i).get(z).setBackgroundResource(R.drawable.shape_white);
                        textlist.get(i).get(z).setTextColor(getResources().getColor(R.color.colorblack));
                    }
                }
            }
        }
    }

    /**
     * 提交按钮事件处理
     */
    class submitOnClickListener implements View.OnClickListener {

        private Page page;

        public submitOnClickListener(Page page) {
            this.page = page;
        }

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            // 判断是否答完题
            boolean isState = true;
            // 最终要的json数组
            JSONArray jsonArray = new JSONArray();
            // 点击提交的时候，先判断状态，如果有未答完的就提示，如果没有再把每条答案提交（包含问卷ID 问题ID 及答案ID）
            // 注：不用管是否是一个问题的答案，就以答案的个数为准来提交上述格式的数据
            for (int i = 0; i < the_quesition_list.size(); i++) {
                the_answer_list = the_quesition_list.get(i).getAnswers();
                // 判断是否有题没答完
                if (the_quesition_list.get(i).getQue_state() == 0) {
                    Toast.makeText(getApplicationContext(), "您第" + (i + 1) + "题没有答完", Toast.LENGTH_LONG).show();
                    jsonArray = null;
                    isState = false;
                    return;
                } else {
                    JSONObject json = new JSONObject();
                    String answers2 = "";
                    String answers = "";
                    for (int j = 0; j < the_answer_list.size(); j++) {
                        if (the_answer_list.get(j).getAns_state() == 1) {
                            try {
                                answers2 = the_quesition_list.get(i).getQuesitionId();
                                if (answers.length()==0){
                                    answers = answers +j;
                                }else {
                                    answers = answers +"-"+ j;
                                }

                                //===为不定项拼接答案================================
                                if (answers.contains("0")) {
                                    answers = answers.replace("0", "A");
                                }
                                if (answers.contains("1")) {
                                    answers = answers.replace("1", "B");
                                }
                                if (answers.contains("2")) {
                                    answers = answers.replace("2", "C");
                                }
                                if (answers.contains("3")) {
                                    answers = answers.replace("3", "D");
                                }
                                if (answers.contains("4")) {
                                    answers = answers.replace("4", "E");
                                }
                                if (answers.contains("5")) {
                                    answers = answers.replace("5", "F");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    try {
                        json.put("answer", answers);
                        json.put("eid", answers2);
//                        Toast.makeText(getApplicationContext(), json + "", Toast.LENGTH_SHORT).show();
                        jsonArray.put(json);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
            Log.d("jsonArray-->", "" + jsonArray);
            Toast.makeText(getApplicationContext(), "提交的数据：" + jsonArray, Toast.LENGTH_SHORT).show();
        }
    }
}
