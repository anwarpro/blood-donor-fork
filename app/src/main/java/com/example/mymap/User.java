package com.example.mymap;

public class User {
    String userId;
    String userName;
    String userPhone;
    String userBlood;

    public User()
    {

    }

    public User(String userId, String userName, String userPhone, String userBlood) {
        this.userId = userId;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userBlood = userBlood;
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
