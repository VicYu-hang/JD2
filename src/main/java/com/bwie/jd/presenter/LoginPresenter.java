package com.bwie.jd.presenter;

import android.text.TextUtils;

import com.bwie.jd.bean.LoginBean;
import com.bwie.jd.bean.LoginCgBean;
import com.bwie.jd.logincallback.LoginCallBack;
import com.bwie.jd.logincallback.LoginnewCallBack;
import com.bwie.jd.model.LoginModel;


/**
 * Created by d on 2017/12/8.
 */

public class LoginPresenter{

    //new出来model对象,presenter可以和model取得联系,model里面是请求网络数据的,数据库等等
    private LoginModel loginModel = new LoginModel();

    //当前传来的 参数是上下文,实现了接口以后,传来的就是接口
    LoginCallBack loginCallBack;
    LoginnewCallBack loginnewCallBack;
    public LoginPresenter(LoginCallBack loginCallBack, LoginnewCallBack loginnewCallBack) {
        this.loginCallBack = loginCallBack;
        this.loginnewCallBack = loginnewCallBack;
    }


    //presenter里面的方法
    public void loginPanduan(String phone, String password) {
        //这里面是逻辑代码,判断输入框是否合法
        if(TextUtils.isEmpty(phone)){
            //接口回调,调用avtivity也就是view里面已经实现了的接口的方法
            loginCallBack.phoneEmpty();
            return;
        }
        if(TextUtils.isEmpty(password)){
            loginCallBack.passwordEmpty();
            return;
        }

        //请求网络数据的方法在model里面,,类名·调用
        loginModel.getData(phone, password, new LoginModel.ModelCallBack() {
            @Override
            public void success(LoginBean loginBean) {
                loginCallBack.loginSuccess(loginBean);
            }

            @Override
            public void failed(int code) {
                loginCallBack.loginFailed(code);
            }
        });
    }


    public void loginnewPanduan(String phone, String password) {
        //这里面是逻辑代码,判断输入框是否合法
        if(TextUtils.isEmpty(phone)){
            //接口回调,调用avtivity也就是view里面已经实现了的接口的方法
            loginCallBack.phoneEmpty();
            return;
        }
        if(TextUtils.isEmpty(password)){
            loginCallBack.passwordEmpty();
            return;
        }

        //请求网络数据的方法在model里面,,类名·调用
        loginModel.getnewData(phone, password, new LoginModel.ModelnewCallBack() {
            @Override
            public void success(LoginCgBean loginBean) {
                loginnewCallBack.loginSuccess(loginBean);
            }

            @Override
            public void failed(int code) {
                loginCallBack.loginFailed(code);
            }
        });
    }



    //防止内存泄露
    public void detach(){
        this.loginCallBack = null;
    }
}
