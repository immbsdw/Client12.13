package com.pku.codingma.client.model;

import java.util.ArrayList;

/**
 * ������
 */

public class Quesition {
    //��Ŀid
    private String quesitionId;
    //��ѡ��ѡ��ʶ
    private String type;//0 ��ѡ 1 ��ѡ
    //��Ŀ
    private String content;
    //ѡ��
    private ArrayList<Answer> answers;
    //�Ƿ���
    private int que_state;

    public int getQue_state() {
        return que_state;
    }

    public void setQue_state(int que_state) {
        this.que_state = que_state;
    }

    public String getQuesitionId() {
        return quesitionId;
    }

    public void setQuesitionId(String quesitionId) {
        this.quesitionId = quesitionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }
}
