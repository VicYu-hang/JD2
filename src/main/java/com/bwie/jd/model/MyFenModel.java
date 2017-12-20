package com.bwie.jd.model;


import com.bwie.jd.app.MyApplication;
import com.bwie.jd.bean.BeanName;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by d on 2017/12/8.
 */

public class MyFenModel {

    public void getData(final ModelCallBack callBack){
        MyApplication.tiGetModel.doGet()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BeanName>() {
                    @Override
                    public void accept(BeanName beanName) throws Exception {
                        callBack.success(beanName);
                    }
                });
    }

    public interface ModelCallBack{
        public void success(BeanName beanName);
    }
}
