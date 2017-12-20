package com.bwie.jd.bean;

/**
 * Created by d on 2017/12/11.
 */

public class ShuaBean {

    public int uid;

    public ShuaBean(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "ShuaBean{" +
                "uid=" + uid +
                '}';
    }
}
