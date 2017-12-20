package com.bwie.jd.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bwie.jd.R;
import com.bwie.jd.adapter.MyAdapter;
import com.bwie.jd.adapter.YouAdapter;
import com.bwie.jd.bean.MutiBean;
import com.bwie.jd.logincallback.MutiViewCallBack;
import com.bwie.jd.presenter.MutiPresenter;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

public class ChaXunActivity extends AppCompatActivity implements MutiViewCallBack {

    private Button button;
    private EditText editText;
    private SpringView springView;
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private MutiPresenter mutiPresenter;
    private ImageView imageView;
    private YouAdapter youAdapter;
    private LinearLayoutManager manager;
    private GridLayoutManager gridLayoutManager;
    String cha = "笔记本";
    int page = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cha_xun);

        button = (Button) findViewById(R.id.main_btn);
        editText = (EditText) findViewById(R.id.main_ed);
        springView = (SpringView) findViewById(R.id.spring);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
        imageView = (ImageView) findViewById(R.id.main_image);

        youAdapter = new YouAdapter(ChaXunActivity.this);
        myAdapter = new MyAdapter(ChaXunActivity.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editText.getText().toString())){
                    Toast.makeText(ChaXunActivity.this,"请输入",Toast.LENGTH_SHORT).show();
                }
                else{
                    if(editText.getText().toString().equals("手机")||editText.getText().toString().equals("笔记本")){
                        cha = editText.getText().toString();
                        mutiPresenter.onRefresh(true,cha,page);
                    }
                }
            }
        });

        imageView.setTag(1);
        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                int tag = (int) imageView.getTag();
                if(tag==1){
                    imageView.setImageResource(R.drawable.grid_icon);
                    imageView.setTag(2);
                    recyclerView.setLayoutManager(manager);
                    recyclerView.setAdapter(myAdapter);
                }
                else{
                    imageView.setTag(1);
                    imageView.setImageResource(R.drawable.lv_icon);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    recyclerView.setAdapter(youAdapter);
                }
            }
        });

        mutiPresenter = new MutiPresenter(this);
        manager = new LinearLayoutManager(ChaXunActivity.this, LinearLayoutManager.VERTICAL, false);
        gridLayoutManager = new GridLayoutManager(ChaXunActivity.this, 2);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myAdapter);

        springView.setFooter(new DefaultFooter(this));
        springView.setHeader(new DefaultHeader(this));
        springView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                mutiPresenter.onRefresh(true, cha, page);
            }

            @Override
            public void onLoadmore() {
                mutiPresenter.onRefresh(false, cha, page);
            }
        });
    }

    @Override
    public void success(MutiBean mutiBean) {
        if(springView !=null){
            springView.onFinishFreshAndLoad();
        }
        myAdapter.add(mutiBean.getData());
        youAdapter.add(mutiBean.getData());
    }

    @Override
    public void failure(Exception e) {
        Toast.makeText(ChaXunActivity.this,"error",Toast.LENGTH_SHORT).show();
    }
}
