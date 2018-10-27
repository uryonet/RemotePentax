package com.uryonet.remotepentax.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoList {

    @SerializedName("errCode")
    @Expose
    private Integer errCode;
    @SerializedName("errMsg")
    @Expose
    private String errMsg;
    @SerializedName("dirs")
    @Expose
    private List<PhotoDir> dirs = null;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<PhotoDir> getDirs() {
        return dirs;
    }

    public void setDirs(List<PhotoDir> dirs) {
        this.dirs = dirs;
    }

}
