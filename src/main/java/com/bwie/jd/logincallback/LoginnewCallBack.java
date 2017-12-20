package com.bwie.jd.logincallback;


import com.bwie.jd.bean.LoginCgBean;

/**
 * Created by d on 2017/12/8.
 */

public interface LoginnewCallBack {


        //接口,,

        //手机号码为空
        public void phoneEmpty();
        //密码为空
        public void passwordEmpty();
        //登录为空
        public void loginSuccess(LoginCgBean loginBean);
        //登录失败
        public void loginFailed(int code);

}
