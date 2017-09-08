package com.gcfhfbj.casadocodigo.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.gcfhfbj.casadocodigo.casadocodigo.CasaDoCodigoApplication;
import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.gcfhfbj.casadocodigo.casadocodigo.adapter.ItensAdapter;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Carrinho;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Item;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android7281 on 06/09/17.
 */

public class CarrinhoActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener {

    @BindView(R.id.lista_itens_carrinho)
    RecyclerView listaItens;

    @BindView(R.id.valor_carrinho)
    TextView valorTotal;

    //private Carrinho carrinho;
    @Inject
    Carrinho carrinho;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);

        //carrinho = ((CasaDoCodigoApplication) this.getApplication()).getCarrinho();
        //List<Item> itens = carrinho.getItens();
        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getApplication();
        app.getComponent().inject(this);

        //ButterKnife.bind(this);
        //Habilitar botão voltar no topo
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        mostrarVoltar();
    }

    private void mostrarVoltar() {
        boolean podeVoltar = getSupportFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(podeVoltar);
    }

    public void carregaLista() {
        listaItens.setAdapter(new ItensAdapter(carrinho.getItens(), this));
        listaItens.setLayoutManager(new LinearLayoutManager(this));

        double total = 0;
        for (Item item : carrinho.getItens()) {
            total += item.getValor();
        }
        valorTotal.setText("R$ " + total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onBackStackChanged() {
        mostrarVoltar();
    }

    //botao voltar
    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}
