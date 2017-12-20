package com.bwie.jd.adapter;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.BeanName;


/**
 * Created by d on 2017/12/8.
 */

public class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.IviewHolder>{
    FragmentActivity activity;
    BeanName beanName;
    public LeftAdapter(FragmentActivity activity, BeanName beanName) {
        this.beanName = beanName;
        this.activity = activity;
    }

    @Override
    public IviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.layout_text,null);
        return new IviewHolder(view);
    }

    @Override
    public void onBindViewHolder(IviewHolder holder, final int position) {
        holder.textView.setText(beanName.getData().get(position).getName());
        //ImageLoader.getInstance().displayImage(beanName.getData().get(position).getIcon(),holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(listener!=null){
                    listener.click(view,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanName.getData().size();
    }

    static class IviewHolder extends RecyclerView.ViewHolder{

        private final TextView textView;
        //private final ImageView imageView;

        public IviewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.left_text);
            //imageView = (ImageView) itemView.findViewById(R.id.left_image);
        }
    }
    onClickListener listener;
    public void setClickListener(onClickListener listener){
        this.listener = listener;
    }

    public interface onClickListener{
        public void click(View view, int position);
    }
}
