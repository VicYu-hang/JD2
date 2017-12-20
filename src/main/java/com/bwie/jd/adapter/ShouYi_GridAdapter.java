package com.bwie.jd.adapter;


import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.SyJiuBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by d on 2017/12/7.
 */

public class ShouYi_GridAdapter extends RecyclerView.Adapter<ShouYi_GridAdapter.IViewHolder>{

    FragmentActivity activity;
    List<SyJiuBean.DataBean> jiuBean;
    public ShouYi_GridAdapter(FragmentActivity activity, List<SyJiuBean.DataBean> jiuBean) {
        this.activity = activity;
        this.jiuBean = jiuBean;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.layout_jiuitem,null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(jiuBean.get(position).getIcon(),holder.imageView);
        holder.textView.setText(jiuBean.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class IViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView textView;

        public IViewHolder(View itemView) {
            super(itemView);

            imageView = (ImageView) itemView.findViewById(R.id.gridsy_image);
            textView = (TextView) itemView.findViewById(R.id.gridsy_text);
        }
    }
}
