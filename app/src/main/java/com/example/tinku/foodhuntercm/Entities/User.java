package com.example.tinku.foodhuntercm.Entities;

/**
 * Created by sandeep on 4/28/2016.
 */

/* Class for handling user information */
public class User {
    private String username;
    private String password;
    private String type;
    private String email;
    private String location;
    private String contactNumber;

    /* Constructor to initialize the important information */
    public User() {
        username = "";
        password = "";
        type = "";
        location = "";
        contactNumber = "";
    }

    /* Getter and Setter methods */
    public void setUserInfo(String uname, String passwd, String usertype,  String userloc, String usercontact){
        username = uname;
        password = passwd;
        type = usertype;
        location = userloc;
        contactNumber = usercontact;
    }

    public void setUserRegisterInfo(String uname, String passwd, String userEmail, String userType){
        username = uname;
        password = passwd;
        email = userEmail;
        type = userType;
    }


    public String getUsername(){ return username; }

    public String getPassword(){ return password; }

    public String getUserType(){ return type; }

    public String getUserEmail(){
        return email;
    }

    public String getUserLocation(){
        return location;
    }

    public String getUserContact(){ return contactNumber;}
}
