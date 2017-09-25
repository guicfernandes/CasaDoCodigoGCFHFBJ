package com.gcfhfbj.casadocodigo.casadocodigo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class CarrinhoActivity extends AppCompatActivity{

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
        //ButterKnife.bind(this);

        //List<Item> itens = carrinho.getItens();
        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getApplication();
        app.getComponent().inject(this);

        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public void carregaLista() {
        listaItens.setAdapter(new ItensAdapter(carrinho.getItens()));
        listaItens.setLayoutManager(new LinearLayoutManager(this));

        double total = 0;
        for (Item item : carrinho.getItens()) {
            total += item.getValor();
        }
        String textoValorTotal = String.format("R$ %.2f", total);
        //valorTotal.setText("R$ " + valorTotal);
        valorTotal.setText(textoValorTotal);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

}
