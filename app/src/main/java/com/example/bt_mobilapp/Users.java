package com.example.bt_mobilapp;

import java.io.Serializable;

public class Users implements Serializable {
    //sayfalar arası veri geçişi olacağı için implement Serializable özelliği katmamız gerekiyor.
    private int user_id;
    private String username;
    private String eMail;
    private String password;
    private String image;

    public Users() {
    }

    public Users(int user_id, String username, String eMail, String password, String image) {
        this.user_id = user_id;
        this.username = username;
        this.eMail = eMail;
        this.password = password;
        this.image = image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
