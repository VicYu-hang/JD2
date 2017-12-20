package com.bwie.jd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd.R;
import com.bwie.jd.bean.EventBean;
import com.bwie.jd.view.DengActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by d on 2017/12/6.
 */

public class WoDe extends Fragment{

    private TextView textView;
    private ImageView imageView;
    private Button shezhi_wode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_wode, container, false);
        textView = (TextView) view.findViewById(R.id.userName);
        imageView = (ImageView) view.findViewById(R.id.my_login);
        shezhi_wode = (Button) view.findViewById(R.id.shezhi_wode);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getActivity()!=null){
            EventBus.getDefault().register(this);
        }

        shezhi_wode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageView.setImageResource(R.drawable.yuan);
                textView.setText("点击登录");
                Toast.makeText(getActivity(),"已注销",Toast.LENGTH_SHORT).show();
            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DengActivity.class);
                startActivity(intent);
            }
        });
    }
    @Subscribe(sticky = true)
    public void onEventMainThread(EventBean eventBean){
        if(eventBean.getMsg().equals("登录成功")){
            imageView.setImageResource(R.drawable.b2d);
            textView.setText("于子航");
        }
    }
}
