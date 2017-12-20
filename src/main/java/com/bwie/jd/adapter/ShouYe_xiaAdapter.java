package com.bwie.jd.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.SyBean;
import com.bwie.jd.view.XiangqActivity;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by d on 2017/12/7.
 */

public class ShouYe_xiaAdapter extends RecyclerView.Adapter<ShouYe_xiaAdapter.IViewHolder>{
    FragmentActivity activity;
    SyBean.TuijianBean tuijian;
    public ShouYe_xiaAdapter(FragmentActivity activity, SyBean.TuijianBean tuijian) {
        this.activity = activity;
        this.tuijian = tuijian;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.layout_xiaadapter, null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, final int position) {
        holder.simpleDraweeView.setImageURI(tuijian.getList().get(position).getImages());
        holder.textView.setText(tuijian.getList().get(position).getTitle());
        holder.simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, XiangqActivity.class);
                        intent.putExtra("url",tuijian.getList().get(position).getDetailUrl());

                        activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tuijian.getList().size();
    }

    public static class IViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final SimpleDraweeView simpleDraweeView;

        public IViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.shouye_xiatext);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.shouye_sim);
        }
    }
}
