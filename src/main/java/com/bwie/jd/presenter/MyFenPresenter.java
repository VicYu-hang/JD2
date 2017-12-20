package com.bwie.jd.presenter;


import com.bwie.jd.bean.BeanName;
import com.bwie.jd.model.MyFenModel;

/**
 * Created by d on 2017/12/8.
 */

public class MyFenPresenter {
    ViewCallBack callBack;
    public MyFenPresenter(ViewCallBack callBack){
    this.callBack = callBack;
    }

    MyFenModel myModel = new MyFenModel();
    public void getData(){
        myModel.getData(new MyFenModel.ModelCallBack() {
            @Override
            public void success(BeanName beanName) {
                callBack.success(beanName);
            }
        });
    }

    public interface ViewCallBack{
        public void success(BeanName beanName);
    }
}
