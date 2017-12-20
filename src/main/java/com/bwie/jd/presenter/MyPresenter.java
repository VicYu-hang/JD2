package com.bwie.jd.presenter;


import com.bwie.jd.bean.CarBean;
import com.bwie.jd.model.MyModel;

/**
 * Created by d on 2017/12/11.
 */

public class MyPresenter {
    ViewCallBack callBack;
    public MyPresenter(ViewCallBack callBack){
        this.callBack = callBack;
    }

    MyModel myModel = new MyModel();

    public void getData(int uid){
        myModel.getData(new MyModel.ModelCallBack() {
            @Override
            public void success(CarBean carBean) {
                callBack.success(carBean);
            }

            @Override
            public void failure(Exception e) {

            }
        },uid);
    }

    public interface ViewCallBack{
        public void success(CarBean carBean);
        public void failure(Exception e);
    }

    public void xiaohui(){
        this.callBack = null;
    }
}
