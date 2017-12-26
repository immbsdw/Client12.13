package com.pku.codingma.client;


import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
    private String usrId;
    private String ursPassword;
    private int usrSex;
    private String userName;
    private int ursType;
    private Date registerTime;

    public User( String usrId,String ursPassword, int usrSex,String userName, int ursType){
        this.usrId = usrId;
        this.ursPassword =ursPassword;
        this.usrSex= usrSex;
        this.userName = userName;
        this.ursType =ursType;
        registerTime = new Date();
    }
    public User()
    {

    };
    public String getUsrId() {
        return usrId;
    }
    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }
    public String getUrsPassword() {
        return ursPassword;
    }
    public void setUrsPassword(String ursPassword) {
        this.ursPassword = ursPassword;
    }
    public int getUsrSex() {
        return usrSex;
    }
    public void setUsrSex(int usrSex) {
        this.usrSex = usrSex;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getUrsType() {
        return ursType;
    }
    public void setUrsType(int ursType) {
        this.ursType = ursType;
    }
    public Date getRegisterTime() {
        return registerTime;
    }
    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

}