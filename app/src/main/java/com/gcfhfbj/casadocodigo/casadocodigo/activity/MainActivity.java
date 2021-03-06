package com.gcfhfbj.casadocodigo.casadocodigo.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gcfhfbj.casadocodigo.casadocodigo.event.LivroEvent;
import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.gcfhfbj.casadocodigo.casadocodigo.delegate.LivrosDelegate;
import com.gcfhfbj.casadocodigo.casadocodigo.fragment.DetalheLivroFragment;
import com.gcfhfbj.casadocodigo.casadocodigo.fragment.ListaLivrosFragment;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;
import com.gcfhfbj.casadocodigo.casadocodigo.server.WebClient;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity implements LivrosDelegate, FragmentManager.OnBackStackChangedListener {

    private ListaLivrosFragment listaLivrosFragment;

    @BindView(R.id.frame_principal)
    View framePrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        listaLivrosFragment = new ListaLivrosFragment();
        transaction.replace(R.id.frame_principal, listaLivrosFragment);
        transaction.commit();

        new WebClient().getLivros();

        EventBus.getDefault().register(this);

        //Habilitar botão voltar no topo
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        mostrarVoltar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void mostrarVoltar() {
        boolean podeVoltar = getSupportFragmentManager().getBackStackEntryCount()>0;
        getSupportActionBar().setDisplayHomeAsUpEnabled(podeVoltar);
    }

    @Override
    public void lidaComLivroSelecionado(Livro livro) {
        //Toast.makeText(this, "Livro selecionado: " + livro.getNome(), Toast.LENGTH_LONG).show();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalheLivroFragment detalhesLivroFragment = criaDetalhesCom(livro);
        transaction.replace(R.id.frame_principal, detalhesLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe
    public void lidaComSucesso(LivroEvent livroEvent) {
        //livroEvent = new LivroEvent(livros);
        listaLivrosFragment.populaListaCom(livroEvent.getLivros());
    }

    @Subscribe
    public void lidaComErro(Throwable erro) {
        //Toast.makeText(this, "Não foi possível carregar os livros...", Toast.LENGTH_SHORT).show();
        Snackbar.make(framePrincipal, "Não foi possível carregar os livros...", Snackbar.LENGTH_SHORT).show();
    }

    private DetalheLivroFragment criaDetalhesCom(Livro livro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("livro", livro);
        DetalheLivroFragment detalhesLivroFragment = new DetalheLivroFragment();
        detalhesLivroFragment.setArguments(bundle);
        return detalhesLivroFragment;
    }

    //botao voltar
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
