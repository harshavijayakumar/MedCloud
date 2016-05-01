package com.example.tinku.foodhuntercm.Entities;

/* Class for handling user information */
public class User {
    private String username;
    private String password;
    private String type;
    private String email;

    /* Constructor to initialize the important information */
    public User() {
        username = "";
        password = "";
        type = "";
    }

    /* Getter and Setter methods */
    public void setUserRegisterInfo(String uname, String passwd, String userEmail, String userType){
        username = uname;
        password = passwd;
        email = userEmail;
        type = userType;
    }


    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String getUserType(){
        return type;
    }

    public String getUserEmail(){
        return email;
    }
}
