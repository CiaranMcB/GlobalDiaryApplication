package com.example.globaldiaryapp;

public class Upload {
    private String mName;
    private String mImageUrl;
    private String mUserID;

    public Upload() {
        //constructor needed
    }

    public Upload(String name, String imageUrl, String userID){
        if (name.trim().equals("")){
            name = "No Name";
        }

        mName = name;
        mImageUrl = imageUrl;
        mUserID = userID;
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
}
