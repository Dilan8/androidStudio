package com.example.app;

import com.google.firebase.database.Exclude;

public class upload {

    private  String mName;
    private  String mId;
    private String mImageUrl;
    private String mName3;
    private  String mName4;
    private String mkey;

    public upload(){

    }

    public upload(String id,String name, String imageUrl, String name3, String name4) {
        if (name.trim().equals("")) {
            name = "No Name";
            name3 = "No Description";
            name4 = "No Url";


        }
        mId = id;
        mName = name;
        mImageUrl = imageUrl;
        mName3 = name3;
        mName4 = name4;


    }


    public String getId() {

        return mId;
    }

    public  void setId(String id) {

        mId = id;
    }

    public String getName() {

        return mName;
    }

    public  void setName(String name) {

        mName = name;
    }

    public String getImageUrl() {

        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {

        mImageUrl = imageUrl;
    }
    public String getName3() {

        return mName3;
    }

    public  void setName3(String name3) {

        mName3 = name3;
    }
    public String getName4() {

        return mName4;
    }

    public  void setName4(String name4) {

        mName4 = name4;
    }

   @Exclude
    public String getKey() {
       return mkey;
    }

    @Exclude
    public void setKey(String key) {
        mkey = key;

    }

}
