package com.bwie.jd.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.jd.R;
import com.bwie.jd.bean.CarBean;
import com.bwie.jd.view.PlusView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * Created by d on 2017/12/11.
 */

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.IViewHolder>{

    Context context;
    //定义图片的集合
    private List<String> ImageList = new ArrayList<>();
    //定义接收数据的集合
    private List<CarBean.DataBean.ListBean> list;
    //
    Map<String,String> map = new Hashtable<>();
    public CarAdapter(Context context) {
        this.context = context;
    }
    @Override
    public IViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.layout_car, null);
        return new IViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final IViewHolder holder, final int position) {
        if(list.get(position).getIsFrist() == 1){
            //显示商家
            holder.shop_check.setVisibility(View.VISIBLE);
            holder.shop_name.setVisibility(View.VISIBLE);
            holder.shop_check.setChecked(list.get(position).isShopSelected());

//            显示商家的名称
//            list.get(position).getSellerid() 取到商家的id
//            map.get（）取到 商家的名称
            holder.shop_name.setText(map.get(String.valueOf(list.get(position).getSellerid())));
        } else {
            holder.shop_check.setVisibility(View.GONE);
            holder.shop_name.setVisibility(View.GONE);
        }

        holder.shoptitle.setText(list.get(position).getTitle());
        holder.shopprice.setText(list.get(position).getPrice()+"");
        holder.checkbox.setChecked(list.get(position).isItemSelected());
        String images = list.get(position).getImages();
        String[] split = images.split("\\|");
        for(int i = 0;i<split.length;i++){
            ImageList.add(split[i]);
        }

        holder.simpleDraweeView.setImageURI(ImageList.get(position));
        holder.plusView.setEditView(list.get(position).getNum());
        holder.shopdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                setFirst(list);
                notifyDataSetChanged();
                sum(list);
            }
        });

        holder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setItemSelected(holder.checkbox.isChecked());

                for(int i = 0;i<list.size();i++){
                    for(int j = 0;j<list.size();j++){
                        if(list.get(i).getSellerid() == list.get(j).getSellerid() && !list.get(j).isItemSelected()){
                            list.get(i).setShopSelected(false);
                            break;
                        }
                        else{
                            list.get(i).setShopSelected(true);
                        }
                    }
                }

                notifyDataSetChanged();
                sum(list);
            }
        });

        holder.plusView.setLinsenter(new PlusView.ClickLisenter() {
            @Override
            public void click(int count) {
                list.get(position).setNum(count);
                notifyDataSetChanged();
                sum(list);
            }
        });

        holder.shop_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.get(position).setShopSelected(holder.shop_check.isChecked());
                for(int i=0; i<list.size();i++){
                    if(list.get(position).getSellerid()==list.get(i).getSellerid()){
                        list.get(i).setItemSelected(holder.shop_check.isChecked());
                    }
                }

                notifyDataSetChanged();
                sum(list);
            }
        });
    }

    public void sum(List<CarBean.DataBean.ListBean> list){
        int totalNum = 0;
        float totalMoney = 0.0f;
        boolean allcheck = true;
        for(int i = 0; i <list.size(); i++){
            if(list.get(i).isItemSelected()){
                totalNum += list.get(i).getNum();
                totalMoney += list.get(i).getPrice()*list.get(i).getNum();
            }
            else{
                allcheck = false;
            }
        }

        listener.setTotal(totalMoney+"",totalNum+"",allcheck);
    }


    @Override
    public int getItemCount() {
        return list==null ? 0 : list.size();
    }

    public void add(CarBean carBean) {
        if(list==null){
            list = new ArrayList<>();
        }
        for(CarBean.DataBean shop:carBean.getData()){
            map.put(shop.getSellerid(),shop.getSellerName());
            for(int i = 0; i<shop.getList().size();i++){
                list.add(shop.getList().get(i));
            }
        }

        setFirst(list);
        notifyDataSetChanged();
    }

    private void setFirst(List<CarBean.DataBean.ListBean> list) {
        if(list.size()>0){
            list.get(0).setIsFrist(1);
            for(int i = 1;i<list.size();i++){
                if(list.get(i).getSellerid()==list.get(i-1).getSellerid()){
                    list.get(i).setIsFrist(2);
                }
                else{
                    list.get(i).setIsFrist(1);
                }
            }
        }
    }


    public void selectAll(boolean check){
        for(int i = 0;i<list.size();i++){
            list.get(i).setShopSelected(check);
            list.get(i).setItemSelected(check);
        }

        notifyDataSetChanged();
        sum(list);
    }

    public static class IViewHolder extends RecyclerView.ViewHolder{

        private final PlusView plusView;
        private final Button shopdel;
        private final CheckBox shop_check;

        private final TextView shop_name;
        private final CheckBox checkbox;
        private final TextView shoptitle;
        private final TextView shopprice;
        private final SimpleDraweeView simpleDraweeView;

        public IViewHolder(View itemView) {
            super(itemView);

            plusView = (PlusView) itemView.findViewById(R.id.plusView);
            shopdel = (Button) itemView.findViewById(R.id.shopdel);
            shop_check = (CheckBox) itemView.findViewById(R.id.shop_check);
            simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sim);
            shop_name = (TextView) itemView.findViewById(R.id.shop_name);
            checkbox = (CheckBox) itemView.findViewById(R.id.checkbox);
            shoptitle = (TextView) itemView.findViewById(R.id.shop_title);
            shopprice = (TextView) itemView.findViewById(R.id.shop_price);
        }
    }

    UpdateUiListener listener;
    public void setListener(UpdateUiListener listener){
        this.listener = listener;
    }

    public interface UpdateUiListener{
        public void setTotal(String total, String num, boolean allcheck);
    }
}
