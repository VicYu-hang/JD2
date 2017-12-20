package com.bwie.jd.adapter;


import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

import com.bwie.jd.R;
import com.bwie.jd.app.GlideImageLoader;
import com.bwie.jd.bean.SyBean;
import com.bwie.jd.bean.SyJiuBean;
import com.bwie.jd.okhttp.AbstractUiCallBack;
import com.bwie.jd.okhttp.OkhttpUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d on 2017/12/6.
 */

public class Shouyi_adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int TYPE_1 = 0;
    public final static int TYPE_2 = 1;
    public final static int TYPE_3 = 2;
    public final static int TYPE_4 = 3;
    public final static int TYPE_5 = 4;
    private List<String> LBlist = new ArrayList<>();
    FragmentActivity activity;
    SyBean bean;
    private RecyclerView.ViewHolder  hoder=null;
    public Shouyi_adapter(FragmentActivity activity, SyBean bean) {
        this.bean = bean;
        this.activity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (TYPE_1 == viewType) {
            view = View.inflate(activity, R.layout.layout_sylb, null);
            return new type1(view);
        }else if(viewType==TYPE_2){
            view = View.inflate(activity, R.layout.layout_grid, null);
            return new type2(view);
        }else if(viewType==TYPE_3){
            view = View.inflate(activity, R.layout.layout_sydonghua, null);
            return new type3(view);}
        else if(viewType==TYPE_4){
            view = View.inflate(activity, R.layout.layout_shang, null);
            return new type4(view);}
        else if(viewType==TYPE_5){
            view = View.inflate(activity, R.layout.layout_syxrecy, null);
            return new type5(view);} else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof type1) {
            List<SyBean.DataBean> data = bean.getData();
            for (int i = 0; i < data.size(); i++) {
                LBlist.add(data.get(i).getIcon());
            }
            ((type1) holder).banner.setImageLoader(new GlideImageLoader());
            ((type1) holder).banner.setImages(LBlist);
            ((type1) holder).banner.start();
        } else if (holder instanceof type2) {
            OkhttpUtils.getInstance().asy(null, "http://120.27.23.105/product/getCatagory", new AbstractUiCallBack<SyJiuBean>() {

                private ShouYi_GridAdapter gridAdapter;

                @Override
                public void success(SyJiuBean jiuBean) {
                    List<SyJiuBean.DataBean> data = jiuBean.getData();
                    gridAdapter = new ShouYi_GridAdapter(activity, data);
                    GridLayoutManager manager = new GridLayoutManager(activity, 5);
                    ((type2) holder).recyclerView.setLayoutManager(manager);
                    ((type2) holder).recyclerView.setAdapter(gridAdapter);
                }

                @Override
                public void failure(Exception e) {

                }
            });

        }

        else if(holder instanceof type3) {
            DraweeController mDraweeController = Fresco.newDraweeControllerBuilder()
                    .setAutoPlayAnimations(true)
                    //设置uri,加载本地的gif资源
                    .setUri(Uri.parse("res://" + activity.getPackageName() + "/" + R.drawable.jingdongdao))//设置uri
                    .build();
            //设置Controller
            ((type3) holder).simpleDraweeView.setController(mDraweeController);
        }

        else if(holder instanceof type4){
            ((type4) holder).viewFlipper.addView(View.inflate(activity,R.layout.item_view,null));
            ((type4) holder).viewFlipper.addView(View.inflate(activity,R.layout.item_view,null));
        }

        else if(holder instanceof type5){
            GridLayoutManager manager = new GridLayoutManager(activity,2);
            ShouYe_xiaAdapter shouYe_xiaAdapter = new ShouYe_xiaAdapter(activity, bean.getTuijian());
            ((type5) holder).recyclerView.setLayoutManager(manager);
            ((type5) holder).recyclerView.setAdapter(shouYe_xiaAdapter);

            /*((type5) holder).recyclerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener!=null){
                        listener.click(view,position);
                    }


                }
            });*/
        }


    }




    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_1;
        } else if (position == 1) {
            return TYPE_2;
        } else if (position == 2) {
            return TYPE_3;
        } else if (position == 3) {
            return TYPE_4;
        } else {
            return TYPE_5;
        }

    }


    @Override
    public int getItemCount() {
        return 5;
    }

        public class type1 extends RecyclerView.ViewHolder {
        Banner banner;

        public type1(View itemView) {
            super(itemView);
            banner = (Banner) itemView.findViewById(R.id.banner);
        }
    }

    public class type2 extends RecyclerView.ViewHolder {

        private final RecyclerView recyclerView;

        public type2(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.recy_gridSY);
        }
    }

    public class type3 extends RecyclerView.ViewHolder {


        private final SimpleDraweeView simpleDraweeView;

        public type3(View itemView) {
            super(itemView);

            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sim_shouye);
        }
    }


    public class type4 extends RecyclerView.ViewHolder {


        private final ViewFlipper viewFlipper;

        public type4(View itemView) {
            super(itemView);
            viewFlipper = (ViewFlipper) itemView.findViewById(R.id.vf);

        }
    }

    public class type5 extends RecyclerView.ViewHolder{

        private final RecyclerView recyclerView;

        public type5(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.shouye_xia);

        }
    }
    /*private  onClickListener listener;
    public void setClickListener(onClickListener listener){
        this.listener = listener;
    }

    public interface onClickListener{
        void click(View view,int postion);
    }*/


}
