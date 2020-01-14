package com.example.mymap;

public class Feed {
    String feedId;
    String feedName;
    String feedAddress;
    String feedBlood;
    String feedPhone;
    String feedAmount;
    String feedDate;
    String feedHosp;
    String feedLocation;
    String feedLocation2;

    public Feed()
    {

    }

    public Feed(String feedId, String feedName, String feedAddress, String feedBlood, String feedPhone, String feedAmount, String feedDate, String feedHosp, String feedLocation, String feedLocation2) {
        this.feedId = feedId;
        this.feedName = feedName;
        this.feedAddress = feedAddress;
        this.feedBlood = feedBlood;
        this.feedPhone = feedPhone;
        this.feedAmount = feedAmount;
        this.feedDate = feedDate;
        this.feedHosp = feedHosp;
        this.feedLocation = feedLocation;
        this.feedLocation2 = feedLocation2;
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
    public String getFeedDate()
    {
        return feedDate;
    }

    public String getFeedHosp() {
        return feedHosp;
    }
    public String getFeedLocation() {
        return feedLocation;
    }
    public String getFeedLocation2() {
        return feedLocation2;
    }
}
