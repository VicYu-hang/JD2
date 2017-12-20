package com.bwie.jd.model;


import com.bwie.jd.bean.MutiBean;
import com.bwie.jd.okhttp.AbstractUiCallBack;
import com.bwie.jd.okhttp.OkhttpUtils;

/**
 * Created by d on 2017/11/13.
 */

public class MutiModel {
    public void Refresh(boolean shuxin, String cha, int page, final ModelCallBack modelCallBack){
        String path ="http://120.27.23.105/product/searchProducts?keywords="+cha+"&page="+page+"&source=android";
        OkhttpUtils.getInstance().asy(null, path, new AbstractUiCallBack<MutiBean>() {
            @Override
            public void success(MutiBean mutiBean) {
                modelCallBack.modelsuccess(mutiBean);
            }

            @Override
            public void failure(Exception e) {
                modelCallBack.modelfailure(e);
            }
        });
    }

    public interface ModelCallBack{
        public void modelsuccess(MutiBean mutiBean);
        public void modelfailure(Exception e);
    }
}
