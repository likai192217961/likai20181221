package com.example.bwie.likai20181221yuekao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bwie.likai20181221yuekao.R;
import com.example.bwie.likai20181221yuekao.bean.ListBean;
import com.example.bwie.likai20181221yuekao.bean.ShouyeBean;

import java.util.List;

public class RecyAdapter extends RecyclerView.Adapter<RecyAdapter.ViewHolder> {
    private List<ShouyeBean.DataBean> list;
    private Context context;

    public RecyAdapter(List<ShouyeBean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.recy_item,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyAdapter.ViewHolder viewHolder, int i) {
        viewHolder.name.setText(list.get(i).getName());
        viewHolder.price.setText(list.get(i).getCreatetime());
        Glide.with(context).load(list.get(i).getIcon()).into(viewHolder.tupian);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView tupian;
        private TextView name;
        private TextView price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tupian=itemView.findViewById(R.id.tupian);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
        }
    }
}
