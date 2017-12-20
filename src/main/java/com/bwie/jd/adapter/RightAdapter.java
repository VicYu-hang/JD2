package com.bwie.jd.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.BeanValue;


/**
 * Created by d on 2017/12/8.
 */

public class RightAdapter extends RecyclerView.Adapter<RightAdapter.IViewHolder>{
    FragmentActivity activity;
    BeanValue beanValue;
    public RightAdapter(FragmentActivity activity, BeanValue beanValue) {
        this.activity = activity;
        this.beanValue = beanValue;
    }

    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.layout_fenright, null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IViewHolder holder, int position) {
        holder.textView.setText(beanValue.getData().get(position).getName());

        GridLayoutManager manager = new GridLayoutManager(activity,3);
        GridAdapter gridAdapter = new GridAdapter(activity,beanValue);
        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setAdapter(gridAdapter);

    }

    @Override
    public int getItemCount() {
        return beanValue.getData().size();
    }

    static class IViewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        private final RecyclerView recyclerView;

        public IViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.right_text);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.grid_recy);
        }
    }
}
