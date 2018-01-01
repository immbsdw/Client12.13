package com.pku.codingma.client;

import android.app.Application;

/**
 * Created by Administrator on 2017/12/31.
 */
public class MyApplication extends Application {
    public  String usrId;
    public  String usrPassword;
    public  int usrSex;
    public  String usrName;
    public  int usrType;

    @Override
    public void onCreate() {

        super.onCreate();
    }

    public String getUsrId() {
        return usrId;
    }
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public void setUsrSex(int usrSex) {
        this.usrSex = usrSex;
    }

    public int getUsrSex() {
        return usrSex;
    }

    public int getUsrType() {
        return usrType;
    }

    public String getUsrName() {
        return usrName;
    }

    public String getUsrPassword() {
        return usrPassword;
    }

    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    public void setUsrPassword(String usrPassword) {
        this.usrPassword = usrPassword;
    }

    public void setUsrType(int usrType) {
        this.usrType = usrType;
    }
}