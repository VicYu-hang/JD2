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
import com.bwie.jd.adapter.LeftAdapter;
import com.bwie.jd.adapter.RightAdapter;
import com.bwie.jd.bean.BeanName;
import com.bwie.jd.bean.BeanValue;
import com.bwie.jd.okhttp.AbstractUiCallBack;
import com.bwie.jd.okhttp.OkhttpUtils;
import com.bwie.jd.presenter.MyFenPresenter;
import com.bwie.jd.view.ChaXunActivity;

/**
 * Created by d on 2017/12/6.
 */

public class FenLei extends Fragment{

    private ImageView fensao_image;
    private RecyclerView liftrecy;
    private RecyclerView right_recy;
    private MyFenPresenter myFenPresenter;
    private ImageView xiaoxi;
    private EditText editText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fenlei, container, false);
        fensao_image = (ImageView) view.findViewById(R.id.fenlei_saoyisao);
        liftrecy = (RecyclerView) view.findViewById(R.id.recy_lift);
        right_recy = (RecyclerView) view.findViewById(R.id.recy_right);
        editText = (EditText) view.findViewById(R.id.fenlei_edit);
        xiaoxi = (ImageView) view.findViewById(R.id.fenlei_xiaoxi);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editText.clearFocus();
        fensao_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivityForResult(new Intent(getActivity(), CaptureActivity.class), 0);
            }
        });

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChaXunActivity.class);
                getActivity().startActivity(intent);
            }
        });

        myFenPresenter = new MyFenPresenter(new MyFenPresenter.ViewCallBack() {
            @Override
            public void success(final BeanName beanName) {
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                LeftAdapter leftAdapter = new LeftAdapter(getActivity(),beanName);
                liftrecy.setAdapter(leftAdapter);
                liftrecy.setLayoutManager(layoutManager);

                leftAdapter.setClickListener(new LeftAdapter.onClickListener() {
                    @Override
                    public void click(View view, int position) {
                        int cid = beanName.getData().get(position).getCid();
                        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getProductCatagory?cid=" + cid, new AbstractUiCallBack<BeanValue>() {
                            @Override
                            public void success(BeanValue beanValue) {
                                LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
                                RightAdapter rightAdapter = new RightAdapter(getActivity(),beanValue);
                                right_recy.setLayoutManager(manager);
                                right_recy.setAdapter(rightAdapter);
                            }

                            @Override
                            public void failure(Exception e) {

                            }
                        });

                    }
                });
            }
        });

        myFenPresenter.getData();


    }
}
