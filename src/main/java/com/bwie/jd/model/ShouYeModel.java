package com.bwie.jd.model;


import com.bwie.jd.bean.SyBean;
import com.bwie.jd.okhttp.AbstractUiCallBack;
import com.bwie.jd.okhttp.OkhttpUtils;

/**
 * Created by d on 2017/12/6.
 */

public class ShouYeModel {

//http://120.27.23.105/ad/getAd

    public void getSYData(final ModelCallBack callBack){
        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/ad/getAd", new AbstractUiCallBack<SyBean>() {
            @Override
            public void success(SyBean bean) {
                callBack.success(bean);
            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

    public interface ModelCallBack{
        public void success(SyBean bean);
    }

}
