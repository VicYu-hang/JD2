package com.bwie.jd.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.adapter.CarAdapter;
import com.bwie.jd.bean.CarBean;
import com.bwie.jd.bean.EventBean;
import com.bwie.jd.presenter.MyPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by d on 2017/12/6.
 */

public class GouCar extends Fragment{
    private MyPresenter myPresenter;
    private RecyclerView recyclerView;
    private TextView zong;
    private TextView liang;
    private CarAdapter adapter;
    private LinearLayoutManager manager;
    private CheckBox quan;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_goucar, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.car_recycle);
        zong = (TextView) view.findViewById(R.id.zong);
        liang = (TextView) view.findViewById(R.id.liang);
        quan = (CheckBox) view.findViewById(R.id.quan);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity()!=null){
            EventBus.getDefault().register(this);
        }




    }



    @Subscribe(sticky = true)
    public void onEventMainThread(EventBean eventBean){
        if(eventBean.getMsg().equals("登录成功")){

            System.out.println("于子航"+eventBean.getUid()+"111"+eventBean.getMsg());


            adapter = new CarAdapter(getActivity());
            //new 布局管理器
            manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            //添加
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            //new Presenter对象
            myPresenter = new MyPresenter(new MyPresenter.ViewCallBack() {
                @Override
                public void success(CarBean carBean) {
                    adapter.add(carBean);
                }

                @Override
                public void failure(Exception e) {

                }
            });

            int uid = eventBean.getUid();
            myPresenter.getData(uid);

            quan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.selectAll(quan.isChecked());
                }
            });
            //设置价格，全选按钮
            adapter.setListener(new CarAdapter.UpdateUiListener() {
                @Override
                public void setTotal(String total, String num, boolean allcheck) {
                    zong.setText("总价是"+total);
                    liang.setText("数量是"+num);
                    quan.setChecked(allcheck);
                }
            });

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        myPresenter.xiaohui();
    }
}
