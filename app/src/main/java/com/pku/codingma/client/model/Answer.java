package com.pku.codingma.client.model;

/**
 * 选项类
 */

public class Answer {
    //选项id
    private String answerId;
    //选项主体
    private String answer_content;
    //选项是否被选择
    private int ans_state;

    public int getAns_state() {
        return ans_state;
    }

    public void setAns_state(int ans_state) {
        this.ans_state = ans_state;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getAnswer_content() {
        return answer_content;
    }

    public void setAnswer_content(String answer_content) {
        this.answer_content = answer_content;
    }
}
