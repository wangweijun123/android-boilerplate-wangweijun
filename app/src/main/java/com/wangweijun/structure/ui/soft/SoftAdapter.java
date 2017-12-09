package com.wangweijun.structure.ui.soft;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wangweijun.structure.R;
import com.wangweijun.structure.data.model.BaseModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangweijun on 2017/11/28.
 */

public class SoftAdapter extends RecyclerView.Adapter<SoftAdapter.MyViewHolder> implements View.OnClickListener, View.OnLongClickListener{
    List<BaseModel> list;
    Context context;

    OnRecyclerViewItemClickListener clickListener;

    OnRecyclerViewItemLongClickListener longClickListener;

    interface OnRecyclerViewItemClickListener {
        void onItemClick(View v);
    }

    interface OnRecyclerViewItemLongClickListener {
        void onItemLongClick(View v);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setOnRecyclerViewItemLongClickListener(OnRecyclerViewItemLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    @Inject
    public SoftAdapter(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }

    public boolean hasData() {
        return list.size() > 0;
    }

    public void addModels(List<BaseModel> newList) {
        list.addAll(list.size(), newList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rank, parent, false);
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BaseModel baseModel = list.get(position);
        holder.nameTextView.setText(baseModel.name);
        holder.itemView.setTag(baseModel);
        Glide.with(context).load(baseModel.icon.url).into(holder.avatarView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClick(v);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longClickListener != null) {
            longClickListener.onItemLongClick(v);
        }
        return true;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageview_hex_color)
        ImageView avatarView;
        @BindView(R.id.text_name)
        TextView nameTextView;
        @BindView(R.id.text_email)
        TextView emailTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
