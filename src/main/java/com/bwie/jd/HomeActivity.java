package com.bwie.jd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bwie.jd.fragment.FaXian;
import com.bwie.jd.fragment.FenLei;
import com.bwie.jd.fragment.GouCar;
import com.bwie.jd.fragment.ShouYe;
import com.bwie.jd.fragment.WoDe;
import com.hjm.bottomtabbar.BottomTabBar;

public class HomeActivity extends AppCompatActivity {

    private BottomTabBar bottomTabBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomTabBar = (BottomTabBar) findViewById(R.id.bottomTab);
        bottomTabBar.init(getSupportFragmentManager())
                .addTabItem("首页",R.mipmap.sy_s,R.mipmap.sy_n, ShouYe.class)
                .addTabItem("分类",R.mipmap.fl_s,R.mipmap.fl_n, FenLei.class)
                .addTabItem("发现",R.mipmap.fx_s,R.mipmap.fx_n, FaXian.class)
                .addTabItem("购物车",R.mipmap.gwc_s,R.mipmap.gwc_n, GouCar.class)
                .addTabItem("我的",R.mipmap.wd_s,R.mipmap.wd_n, WoDe.class);
    }
}
