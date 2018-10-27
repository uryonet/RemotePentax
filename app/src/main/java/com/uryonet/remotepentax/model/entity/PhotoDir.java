package com.uryonet.remotepentax.model.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoDir {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("files")
    @Expose
    private List<String> files = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

}
