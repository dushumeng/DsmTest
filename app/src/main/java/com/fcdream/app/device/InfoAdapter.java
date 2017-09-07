package com.fcdream.app.device;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fcdream.app.device.info.BaseInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2017, 北京视达科科技有限责任公司 All rights reserved.
 * author：shumeng.du
 * date：2017/9/7
 * description：
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.MyViewHolder> {

    private final List<BaseInfo> dataList = new ArrayList<>();

    private Context context;

    public InfoAdapter(Context context) {
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.adapter_info_item, viewGroup, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
        myViewHolder.infoView.setText(dataList.get(i).info);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        final public TextView infoView;

        public MyViewHolder(View itemView) {
            super(itemView);

            infoView = (TextView) itemView.findViewById(R.id.info);
        }
    }

    public List<BaseInfo> getDataList() {
        return dataList;
    }
}
