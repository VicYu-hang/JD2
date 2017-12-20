package com.bwie.jd.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.bwie.jd.R;
import com.bwie.jd.adapter.Shouyi_adapter;
import com.bwie.jd.bean.SyBean;
import com.bwie.jd.presenter.ShouYePresenter;
import com.bwie.jd.view.ChaXunActivity;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by d on 2017/12/6.
 */

public class ShouYe extends Fragment{

    private EditText editText;
    private RecyclerView recyclerView;
    private ImageView saoimageView;
    private ImageView xiaoxi;
    private ShouYePresenter presenter;
    private Shouyi_adapter shouyiAdapter;
    private LinearLayoutManager manager;
    private SpringView springView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_shou, container, false);
        editText = (EditText) view.findViewById(R.id.shouye_edit);
        recyclerView = (RecyclerView) view.findViewById(R.id.shouye_recycle);
        saoimageView = (ImageView) view.findViewById(R.id.shouye_saoyisao);
        xiaoxi = (ImageView) view.findViewById(R.id.shouye_xiaoxi);
        springView = (SpringView) view.findViewById(R.id.shou_spring);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editText.clearFocus();
        saoimageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 0);
            }
        });
        manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        presenter = new ShouYePresenter(new ShouYePresenter.ViewCallBack() {
            @Override
            public void success(final SyBean bean) {
                shouyiAdapter = new Shouyi_adapter(getActivity(),bean);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(shouyiAdapter);
            }
        });

        presenter.getSYData();
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChaXunActivity.class);
                getActivity().startActivity(intent);
            }
        });



        springView.setHeader(new DefaultHeader(getActivity()));
        springView.setFooter(new DefaultFooter(getActivity()));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                springView.onFinishFreshAndLoad();

            }

            @Override
            public void onLoadmore() {
                springView.onFinishFreshAndLoad();

            }
        });


    }


}
