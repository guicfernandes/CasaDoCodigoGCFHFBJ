package com.gcfhfbj.casadocodigo.casadocodigo.fragment;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.gcfhfbj.casadocodigo.casadocodigo.CasaDoCodigoApplication;
import com.gcfhfbj.casadocodigo.casadocodigo.ColorUpdater;
import com.gcfhfbj.casadocodigo.casadocodigo.EndlessListListener;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Autor;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;
import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.gcfhfbj.casadocodigo.casadocodigo.adapter.LivroAdapter;
import com.gcfhfbj.casadocodigo.casadocodigo.server.WebClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android7281 on 04/09/17.
 */

public class ListaLivrosFragment extends Fragment {

    private List<Livro> livros = new ArrayList<>();

    @BindView(R.id.lista_livros)
    RecyclerView recyclerView;

    @Inject
    FirebaseRemoteConfig config;

    ColorUpdater colorUpdater;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        ButterKnife.bind(this, view);

        /*
        List<Livro> livros = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            Autor autor = new Autor();
            autor.setNome("Autor " + i);
            //Livro livro = new Livro("Livro " + i, "Descrição " + i, Arrays.asList(autor), i + 100, "10/02/1998", "1548700", 10.50, 15.15);
            Livro livro = new Livro("Livro " + i, "Descricao " + i, Arrays.asList(autor));
            livros.add(livro);
        }
        */

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        app.getComponent().inject(this);

        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        config.fetch(30).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    config.activateFetched();
                    boolean listaSimples = config.getBoolean("lista_simples");
                    //setBarsColor(config.getString("cor_toolbar"), config.getString("cor_statusbar"));
                    colorUpdater = new ColorUpdater(config);
                    //new LivroAdapter(livros, listaSimples);
                    configuraLista(listaSimples);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
        //return super.onCreateView(inflater, container, savedInstanceState);
    }

    //===========================

    /*private AppCompatActivity activity;
    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setBarsColor(String toolbarColorStr, String statusbarColorStr) {
        int toolbarColor = Color.parseColor(toolbarColorStr);
        int statusbarColor = Color.parseColor(statusbarColorStr);

        //configura cor toolbar
        activity.getSupportActionBar().setBackgroundDrawable(new ColorDrawable(toolbarColor));

        //configura cor da status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(statusbarColor);
        }
    }*/

    //=========================

    private void configuraLista(boolean listaSimples) {
        //RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.lista_livros);
        recyclerView.setAdapter(new LivroAdapter(livros, listaSimples));
    }


    public void populaListaCom(final List<Livro> livros) {
        //this.livros.clear();
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();

        //recyclerView.addOnScrollListener(new EndlessListListener());
        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            public void carregaMaisItens() {
                new WebClient().getLivros(livros.size(), 10);
            }
        });
    }
}
