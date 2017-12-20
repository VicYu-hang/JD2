package com.bwie.jd.logincallback;


import com.bwie.jd.bean.LoginBean;

/**
 * Created by d on 2017/12/8.
 */

public interface LoginCallBack {


        //接口,,

        //手机号码为空
        public void phoneEmpty();
        //密码为空
        public void passwordEmpty();
        //登录为空
        public void loginSuccess(LoginBean loginBean);
        //登录失败
        public void loginFailed(int code);

}
