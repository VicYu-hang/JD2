package com.bwie.jd.presenter;


import com.bwie.jd.bean.SyBean;
import com.bwie.jd.model.ShouYeModel;

/**
 * Created by d on 2017/12/6.
 */

public class ShouYePresenter {
    ViewCallBack callBack;
    public ShouYePresenter(ViewCallBack callBack){
        this.callBack = callBack;
    }
    ShouYeModel shouYeModel = new ShouYeModel();
    public void getSYData(){

        shouYeModel.getSYData(new ShouYeModel.ModelCallBack() {
            @Override
            public void success(SyBean bean) {
                callBack.success(bean);
            }
        });
    }

    public interface ViewCallBack{
        public void success(SyBean bean);
    }


}
