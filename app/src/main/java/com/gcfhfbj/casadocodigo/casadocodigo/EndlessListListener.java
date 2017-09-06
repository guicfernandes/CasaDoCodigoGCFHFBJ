package com.gcfhfbj.casadocodigo.casadocodigo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by android7281 on 06/09/17.
 */

public abstract class EndlessListListener extends RecyclerView.OnScrollListener {
    private int quantidadeTotalItens;
    private int primeitoItemVisivel;
    private int quantidadeItensVisiveis;
    private boolean carregando = true;
    private int totalAnterior = 0;

    public abstract void carregaMaisItens();

    @Override
    public void onScrolled(RecyclerView recyclerView, int qtdScrollHorizontal, int qtdScrollVertical) {
        super.onScrolled(recyclerView, qtdScrollHorizontal, qtdScrollVertical);

        int indiceLimiteParaCarregar = quantidadeTotalItens - quantidadeItensVisiveis - 5;

        LinearLayoutManager layoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();

        quantidadeTotalItens = layoutManager.getItemCount();
        primeitoItemVisivel = layoutManager.findFirstVisibleItemPosition();
        quantidadeItensVisiveis = layoutManager.getChildCount();

        if (carregando) {
            if (quantidadeTotalItens > totalAnterior) {
                totalAnterior = quantidadeTotalItens;
                carregando = false;
            }
        }
        if (!carregando && primeitoItemVisivel >= indiceLimiteParaCarregar) {
            carregaMaisItens();
            carregando = true;
        }
    }
}
