package com.bwie.jd.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.MutiBean;
import com.bwie.jd.view.XiangActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by d on 2017/11/13.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ImageHolder>{

    Context context;
    public MyAdapter(Context context) {
       this.context = context;
    }
    private List<MutiBean.DataBean> listda;
    public void add(List<MutiBean.DataBean> list){
        if(listda==null){
            listda = new ArrayList<>();
        }
        listda.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layoutcha_list, null);
        ImageHolder imageHolder = new ImageHolder(view);
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, final int position) {
        String[] split = listda.get(position).getImages().split("'|'");

        ImageLoader.getInstance().displayImage(split[split.length-1],holder.imageView);
        holder.textView.setText(listda.get(position).getTitle());
        /*holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, XiangActivity.class);
                intent.putExtra("pid",listda.get(position).getPid());
                context.startActivity(intent);
            }
        });*/

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("dealerid", listda.get(position).getPid()+"");
                Intent intent = new Intent(context,XiangActivity.class);
                intent.putExtras(bundle);
                System.out.println("ID1111"+listda.get(position).getPid());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listda==null?0:listda.size();
    }

    static class ImageHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView textView;

        public ImageHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.listcha_image);
            textView = (TextView) itemView.findViewById(R.id.listcha_text);
        }
    }
}
