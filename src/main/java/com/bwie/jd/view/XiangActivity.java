package com.bwie.jd.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.jd.R;
import com.bwie.jd.app.GlideImageLoader;
import com.bwie.jd.bean.EventBean;
import com.bwie.jd.bean.JiaBean;
import com.bwie.jd.bean.XiangBean;
import com.bwie.jd.okhttp.AbstractUiCallBack;
import com.bwie.jd.okhttp.OkhttpUtils;
import com.youth.banner.Banner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class XiangActivity extends AppCompatActivity {

    private Button xiang_btn;
   // private ImageView imageView;
    private TextView xiang_name;
    private TextView xiang_title;
    private TextView xiang_price;
    private Banner banner;
    private String pid;
    public int name = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiang);

        xiang_btn = (Button) findViewById(R.id.xiang_btn);
        //imageView = (ImageView) findViewById(R.id.xiang_image);
        xiang_name = (TextView) findViewById(R.id.xiang_name);
        xiang_title = (TextView) findViewById(R.id.xiang_title);
        xiang_price = (TextView) findViewById(R.id.xiang_price);

        banner = (Banner) findViewById(R.id.xiang_banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        pid = (String) getIntent().getExtras().get("dealerid");
        System.out.println("liqiwen==="+ pid);
        Toast.makeText(XiangActivity.this, pid,Toast.LENGTH_SHORT).show();

        EventBus.getDefault().register(this);
        xiang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("添加"+name);
                OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/addCart?uid="+name+"&pid=" + pid + "&source=android", new AbstractUiCallBack<JiaBean>() {
                    @Override
                    public void success(JiaBean jiaBean) {
                        Toast.makeText(XiangActivity.this,"加购成功",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void failure(Exception e) {

                    }
                });
            }
        });


        OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getProductDetail?pid=" + pid + "&source=android", new AbstractUiCallBack<XiangBean>() {
            @Override
            public void success(XiangBean xiangBean) {
                xiang_name.setText(xiangBean.getSeller().getName());
                xiang_title.setText(xiangBean.getData().getTitle());
                xiang_price.setText(xiangBean.getData().getPrice()+"");
                String images = xiangBean.getData().getImages();
                String[] split = images.split("\\|");
                List<String> iamgeslist = new ArrayList<>();
                for(int i = 0; i <split.length;i++){
                    iamgeslist.add(split[i]);
                }
                //设置图片集合
                banner.setImages(iamgeslist);
                //banner设置方法全部调用完毕时最后调用
                banner.start();

            }

            @Override
            public void failure(Exception e) {

            }
        });
    }

    @Subscribe(sticky = true)
    public void onEventMainThread(EventBean eventBean){
        name = eventBean.getUid();
        System.out.println(name);
    }
}
