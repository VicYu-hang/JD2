package com.bwie.jd.adapter;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.BeanValue;
import com.bwie.jd.view.XiangActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by d on 2017/12/8.
 */

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.IViewHolder>{
    FragmentActivity activity;
    BeanValue beanValue;
    public GridAdapter(FragmentActivity activity, BeanValue beanValue) {
        this.activity = activity;
        this.beanValue = beanValue;
    }



    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.layout_fengrid, null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        holder.textView.setText(beanValue.getData().get(position).getList().get(position).getName());
        ImageLoader.getInstance().displayImage(beanValue.getData().get(position).getList().get(position).getIcon(),holder.imageView);
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, XiangActivity.class);
                intent.putExtra("","");
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanValue.getData().size();
    }

    public static class IViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final ImageView imageView;

        public IViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.grid_text);
            imageView = (ImageView) itemView.findViewById(R.id.grid_image);
        }
    }
}
