package com.bwie.jd.model;

import com.bwie.jd.bean.LoginBean;
import com.bwie.jd.bean.LoginCgBean;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by d on 2017/12/8.
 */

public class LoginModel {

    //model里面是请求网络数据的方法,,,getData
    public void getData(String phone, String password, final ModelCallBack modelCallBack) {
        //使用OKHttpclient
        OkHttpClient client = new OkHttpClient();

        //post请求方式,需要传入一个requestbody
        RequestBody body = new FormBody.Builder()
                .add("mobile",phone)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .post(body)
                .url("http://120.27.23.105/user/login")
                .build();

        //异步请求,,在子线程里
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //回调modelcallback里面的接口,,将1传过去presenter
                modelCallBack.failed(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Gson gson = new Gson();
                LoginBean loginBean = gson.fromJson(data, LoginBean.class);
                //回调modelcallback里面的接口,,将请求到的数据传回去presenter
                modelCallBack.success(loginBean);
            }
        });

    }

    public void getnewData(String phone, String password, final ModelnewCallBack modelCallBack) {
        //使用OKHttpclient
        OkHttpClient client = new OkHttpClient();

        //post请求方式,需要传入一个requestbody
        RequestBody body = new FormBody.Builder()
                .add("mobile",phone)
                .add("password",password)
                .build();

        Request request = new Request.Builder()
                .post(body)
                .url("http://120.27.23.105/user/reg")
                .build();

        //异步请求,,在子线程里
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //回调modelcallback里面的接口,,将1传过去presenter
                modelCallBack.failed(1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                Gson gson = new Gson();
                LoginCgBean loginBean = gson.fromJson(data, LoginCgBean.class);
                //回调modelcallback里面的接口,,将请求到的数据传回去presenter
                modelCallBack.success(loginBean);
            }
        });

    }

    public interface ModelCallBack {
        //model的接口.,
        public void success(LoginBean loginBean);
        public void failed(int code);
    }

    public interface ModelnewCallBack {
        //model的接口.,
        public void success(LoginCgBean loginBean);
        public void failed(int code);
    }
}
