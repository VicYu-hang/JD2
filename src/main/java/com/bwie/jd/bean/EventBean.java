package com.bwie.jd.bean;

/**
 * Created by d on 2017/12/8.
 */

public class EventBean {

    String msg;
    int uid;

    public EventBean(String msg, int uid) {
        this.msg = msg;
        this.uid = uid;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "EventBean{" +
                "msg='" + msg + '\'' +
                ", uid=" + uid +
                '}';
    }
}
