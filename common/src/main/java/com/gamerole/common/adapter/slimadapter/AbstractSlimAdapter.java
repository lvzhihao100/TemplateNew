package com.gamerole.common.adapter.slimadapter;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


abstract class AbstractSlimAdapter extends RecyclerView.Adapter<SlimViewHolder> {


    @Override
    public  void onBindViewHolder(SlimViewHolder holder, int position) {
        holder.bind(getItem(position ), position, null);
    }

    @Override
    public void onBindViewHolder(SlimViewHolder holder, int position, List<Object> payloads) {
        holder.bind(getItem(position ), position ,payloads);
    }

    protected abstract Object getItem(int position);


}
