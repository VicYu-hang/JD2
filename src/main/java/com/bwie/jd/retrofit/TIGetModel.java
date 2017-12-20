package com.bwie.jd.retrofit;

import com.bwie.jd.bean.BeanName;
import com.bwie.jd.bean.CarBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by d on 2017/12/8.
 */

public interface TIGetModel {
    @GET("/product/getCatagory")
    Observable<BeanName> doGet();

    @GET("/product/getCarts")
    Call<CarBean> doGetmodel(@Query("uid") int uid);
}
