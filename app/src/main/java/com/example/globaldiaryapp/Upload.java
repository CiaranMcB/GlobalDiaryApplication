package com.example.globaldiaryapp;

import java.util.Map;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mUserID;
    private Object mTimestamp;

    public Upload() {
        //constructor needed
    }

    public Upload(String name, String imageUrl, String userID, Object timestamp){
        if (name.trim().equals("")){
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mUserID = userID;
        mTimestamp = timestamp;
    }

    public Upload(String trim, String toString, Map<String, String> timestamp) {

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

    public Object getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Object timestamp) {
        mTimestamp = timestamp;
    }

}
