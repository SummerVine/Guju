package com.example.guju.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/7/9.
 */
@Table(name = "user")
public class User {
    @Column(column = "id")
    private int id;
    @Column(column = "userName")
    private String userName;
    @Column(column = "userPwd")
    private String userPwd;
    public User (){}
    public User(String userName,String userPwd){
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
