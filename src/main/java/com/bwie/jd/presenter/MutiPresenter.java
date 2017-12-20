package com.bwie.jd.presenter;


import com.bwie.jd.bean.MutiBean;
import com.bwie.jd.logincallback.MutiViewCallBack;
import com.bwie.jd.model.MutiModel;

/**
 * Created by d on 2017/11/13.
 */

public class MutiPresenter {

    MutiModel mutiModel = new MutiModel();
    MutiViewCallBack mutiViewCallBack;
    public MutiPresenter(MutiViewCallBack mutiViewCallBack) {
        this.mutiViewCallBack = mutiViewCallBack;
    }

    public void onRefresh(boolean shuxin, String cha, int page){
    mutiModel.Refresh(shuxin,cha,page, new MutiModel.ModelCallBack() {
        @Override
        public void modelsuccess(MutiBean mutiBean) {
            mutiViewCallBack.success(mutiBean);
        }

        @Override
        public void modelfailure(Exception e) {
            mutiViewCallBack.failure(e);
        }
    });
    }
}
