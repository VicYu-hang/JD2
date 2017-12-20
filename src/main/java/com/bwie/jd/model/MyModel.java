package com.bwie.jd.model;

import com.bwie.jd.app.MyApplication;
import com.bwie.jd.bean.CarBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by d on 2017/12/11.
 */

public class MyModel {

    public void getData(final ModelCallBack callBack, int uid){
        Call<CarBean> call = MyApplication.tiGetModel.doGetmodel(uid);

        call.enqueue(new Callback<CarBean>() {
            @Override
            public void onResponse(Call<CarBean> call, Response<CarBean> response) {
                CarBean carBean = response.body();
                callBack.success(carBean);
            }

            @Override
            public void onFailure(Call<CarBean> call, Throwable t) {

            }
        });
    }

    public interface ModelCallBack{
        public void success(CarBean carBean);
        public void failure(Exception e);
    }
}
