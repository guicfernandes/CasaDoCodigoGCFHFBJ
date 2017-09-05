package com.gcfhfbj.casadocodigo.casadocodigo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcfhfbj.casadocodigo.casadocodigo.delegate.LivrosDelegate;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;
import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android7281 on 04/09/17.
 */

public class LivroAdapter extends RecyclerView.Adapter {
    private List<Livro> livros;

    public LivroAdapter(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int tipoDeLayout = R.layout.item_livro_par;
        if(viewType % 2 != 0) {
            tipoDeLayout = R.layout.item_livro_impar;
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(tipoDeLayout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Livro livro = livros.get(position);
        viewHolder.nome.setText(livro.getNome());
        //viewHolder.descricao.setText(livro.getDescricao());

        Picasso.with(viewHolder.foto.getContext())
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .into(viewHolder.foto);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        //TextView nome;
        //ImageView foto;
        //TextView descricao;

        @BindView(R.id.item_livro_foto)
        ImageView foto;

        @BindView(R.id.item_livro_nome)
        TextView nome;

        public ViewHolder(View view) {
            super(view);

            ButterKnife.bind(this, view);
            //nome = (TextView) view.findViewById(R.id.item_livro_nome);
            //descricao = (TextView) view.findViewById(R.id.item_livro_descricao);
            //foto = (ImageView) view.findViewById(R.id.item_livro_foto);
        }

        @OnClick(R.id.item_livro)
        public void clickItem() {
            Livro livro = livros.get(getAdapterPosition());
            LivrosDelegate delegate = (LivrosDelegate) itemView.getContext();
            delegate.lidaComLivroSelecionado(livro);
        }
    }
}
