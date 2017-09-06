package com.gcfhfbj.casadocodigo.casadocodigo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gcfhfbj.casadocodigo.casadocodigo.CasaDoCodigoApplication;
import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.gcfhfbj.casadocodigo.casadocodigo.activity.CarrinhoActivity;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Autor;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Carrinho;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Item;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;
import com.gcfhfbj.casadocodigo.casadocodigo.model.TipoDeCompra;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android7281 on 04/09/17.
 */

public class DetalheLivroFragment extends Fragment {

    private Carrinho carrinho;
    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;

    @BindView(R.id.detalhes_livro_autores)
    TextView autores;

    @BindView(R.id.detalhes_livro_comprar_fisico)
    Button botaoComprarFisico;

    @BindView(R.id.detalhes_livro_comprar_ebook)
    Button botaoComprarEbook;

    @BindView(R.id.detalhes_livro_comprar_ambos)
    Button botaoComprarAmbos;

    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;

    @BindView(R.id.detalhes_livro_num_paginas)
    TextView numPaginas;

    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;

    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    private Livro livro;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);
        ButterKnife.bind(this, view);

        Bundle arguments =  getArguments();
        livro = (Livro) arguments.getSerializable("livro");
        populaCamposCom(livro);
        carrinho = ((CasaDoCodigoApplication) getActivity().getApplication()).getCarrinho();

        return view;
    }

    private void populaCamposCom(Livro livro) {
        nome.setText(livro.getNome());

        String listaDeAutores = "";
        for (Autor autor : livro.getAutores()) {
            if (!listaDeAutores.isEmpty()) {
                listaDeAutores += ", ";
            }
            listaDeAutores += autor.getNome();
        }
        autores.setText(listaDeAutores);

        descricao.setText(livro.getDescricao());
        numPaginas.setText(String.valueOf(livro.getNumPaginas()));
        isbn.setText(livro.getISBN());
        dataPublicacao.setText(livro.getDataPublicacao());

        String textoComprarFisico = String.format("Comprar Livro Físico - R$ %.2f", livro.getValorFisico());
        botaoComprarFisico.setText(textoComprarFisico);

        String textoComprarEbook = String.format("Comprar E-book - R$ %.2f", livro.getValorVirtual());
        botaoComprarEbook.setText(textoComprarEbook);

        String textoComprarAmbos = String.format("Comprar Ambos - R$ %.2f", livro.getValorDoisJuntos());
        botaoComprarAmbos.setText(textoComprarAmbos);

        Picasso.with(getContext()).load(livro.getUrlFoto()).placeholder(R.drawable.livro).into(foto);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.vai_para_carrinho:
                Intent vaiParaCarrinho = new Intent(getActivity(), CarrinhoActivity.class);
                startActivity(vaiParaCarrinho);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void onClickComprarFisico() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.FISICO));
        //startActivity(new Intent(getActivity(), CarrinhoActivity.class));
    }

    @OnClick(R.id.detalhes_livro_comprar_ebook)
    public void onClickComprarVirtual() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.VIRTUAL));
        //startActivity(new Intent(getActivity(), CarrinhoActivity.class));
    }

    @OnClick(R.id.detalhes_livro_comprar_ambos)
    public void onClickComprarAmbos() {
        carrinho.adiciona(new Item(livro, TipoDeCompra.JUNTOS));
        startActivity(new Intent(getActivity(), CarrinhoActivity.class));
    }
}
