package com.gcfhfbj.casadocodigo.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.gcfhfbj.casadocodigo.casadocodigo.activity.CarrinhoActivity;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Item;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android7281 on 06/09/17.
 */

public class ItensAdapter extends RecyclerView.Adapter {

    private List<Item> itens;
    private CarrinhoActivity carrinhoActivity;

    public ItensAdapter(List<Item> itens) {
        this.itens = itens;
        //this.carrinhoActivity = carrinhoActivity;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Item item = itens.get(position);
        Livro livro = item.getLivro();
        viewHolder.nomeItemComprado.setText(livro.getNome());
        viewHolder.valorItemComprado.setText(String.valueOf(item.getValor()));
        Picasso.with(viewHolder.imgItemComprado.getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.imgItemComprado);
    }

    @Override
    public int getItemCount() {
        return itens.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carrinho, parent, false);
        //View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_carrinho, parent, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imagem_item_comprado)
        ImageView imgItemComprado;

        @BindView(R.id.nome_item_comprado)
        TextView nomeItemComprado;

        @BindView(R.id.valor_pago_item_comprado)
        TextView valorItemComprado;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}
