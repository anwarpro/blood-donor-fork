package com.example.mymap;

public class User {
    String userId;
    String userName;
    String userPhone;
    String userBlood;
    String userEmail;

    public User()
    {

    }

    public User(String userId, String userName, String userPhone, String userBlood, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBlood = userBlood;
        this.userEmail = userEmail;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserBlood() {
        return userBlood;
    }
}
