package com.bwie.jd.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bwie.jd.R;
import com.bwie.jd.bean.MutiBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by d on 2017/11/13.
 */

public class YouAdapter extends RecyclerView.Adapter<YouAdapter.ImageHolder>{

    Context context;
    public YouAdapter(Context context) {
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
        View view = View.inflate(context, R.layout.layoutcha_grid, null);
        ImageHolder imageHolder = new ImageHolder(view);
        return imageHolder;
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {
        String[] split = listda.get(position).getImages().split("'|'");

        ImageLoader.getInstance().displayImage(split[split.length-1],holder.imageView);
        holder.textView.setText(listda.get(position).getTitle());
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
            imageView = (ImageView) itemView.findViewById(R.id.gridcha_image);
            textView = (TextView) itemView.findViewById(R.id.gridcha_text);
        }
    }
}
