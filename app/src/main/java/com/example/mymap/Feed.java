package com.example.mymap;

public class Feed {
    String feedId;
    String feedName;
    String feedAddress;
    String feedBlood;
    String feedPhone;
    String feedAmount;

    public Feed()
    {

    }

    public Feed(String feedId, String feedName, String feedAddress, String feedBlood, String feedPhone, String feedAmount) {
        this.feedId = feedId;
        this.feedName = feedName;
        this.feedAddress = feedAddress;
        this.feedBlood = feedBlood;
        this.feedPhone = feedPhone;
        this.feedAmount = feedAmount;
    }

    public String getFeedId() {
        return feedId;
    }

    public String getFeedName() {
        return feedName;
    }

    public String getFeedAddress() {
        return feedAddress;
    }

    public String getFeedBlood() {
        return feedBlood;
    }

    public String getFeedPhone() {
        return feedPhone;
    }

    public String getFeedAmount() {
        return feedAmount;
    }
}
