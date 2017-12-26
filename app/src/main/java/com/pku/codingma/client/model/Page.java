package com.pku.codingma.client.model;

import java.util.ArrayList;

/**
 * 试卷
 */

public class Page {
    //小测id
    private String pageId;
    //小测状态
    private String status;
    //小测主题
    private String title;
    //题目
    private ArrayList<Quesition> quesitions;

    public ArrayList<Quesition> getQuesitions() {
        return quesitions;
    }

    public void setQuesitions(ArrayList<Quesition> quesitions) {
        this.quesitions = quesitions;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
