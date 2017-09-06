package com.gcfhfbj.casadocodigo.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.gcfhfbj.casadocodigo.casadocodigo.activity.CarrinhoActivity;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Item;

import java.util.List;

/**
 * Created by android7281 on 06/09/17.
 */

public class ItensAdapter extends RecyclerView.Adapter {
    public ItensAdapter(List<Item> itens, CarrinhoActivity carrinhoActivity) {
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }
}
