package com.example.app;

import com.google.firebase.database.Exclude;

public class Upload_res {
    private String mName1;
    private String mImageUrl1;
    private String mDes;
    private String mUrl;
    private String mKey;
    private String mID;


    public Upload_res() {
        //empty constructor needed
    }

    public Upload_res(String ID,String name, String imageUrl, String des, String url) {
        if (name.trim().equals("")) {
            name = "No Name";
            des = "No Description";
            url = "No Url";
        }
        mID = ID;
        mName1 = name;
        mDes = des;
        mUrl = url;
        mImageUrl1 = imageUrl;
    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getName() {
        return mName1;
    }

    public void setName(String name) {
        mName1 = name;
    }

    public String getImageUrl() {
        return mImageUrl1;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl1 = imageUrl;
    }

    public String getDes() {
        return mDes;
    }

    public void setDes(String des) {
        mDes = des;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }
}
