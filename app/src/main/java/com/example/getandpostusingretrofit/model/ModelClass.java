package com.example.getandpostusingretrofit.model;

import com.google.gson.annotations.SerializedName;

public class ModelClass {

    @SerializedName("id")
    private String id;

    @SerializedName("data")
    private String data;

    public ModelClass(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }
}
