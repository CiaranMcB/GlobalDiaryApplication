package com.example.globaldiaryapp;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mUserID;
    private String mMood;
    private String mDate;

    public Upload() {
        //constructor needed
    }

    public Upload(String name, String imageUrl, String userID, String mood, String date){
        if (name.trim().equals("")){
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mUserID = userID;
        mMood = mood;
        mDate = date;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }

    public String getUserID(){
        return mUserID;
    }

    public void setUserID(String userID) {
        mUserID = userID;
    }

    public String getMood() {
        return mMood;
    }

    public void setMood(String mood) {
        mMood = mood;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
