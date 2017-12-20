package com.bwie.jd.logincallback;


import com.bwie.jd.bean.MutiBean;

/**
 * Created by d on 2017/11/13.
 */

public interface MutiViewCallBack {
    public void success(MutiBean mutiBean);
    public void failure(Exception e);
}
