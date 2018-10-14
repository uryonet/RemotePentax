package com.uryonet.remotepentax.service.model;

import java.util.List;

public class PhotoList {
    private int errCode;
    private String errMsg;
    private List<Dirs> dirs;

    public PhotoList(int errCode, String errMsg, List<Dirs> dirs) {
        this.errCode = errCode;
        this.errMsg = errMsg;
        this.dirs = dirs;
    }

    public int getErrCode() {
        return errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public List<Dirs> getDirs() {
        return dirs;
    }
}
