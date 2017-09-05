package com.gcfhfbj.casadocodigo.casadocodigo.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.gcfhfbj.casadocodigo.casadocodigo.delegate.LivrosDelegate;
import com.gcfhfbj.casadocodigo.casadocodigo.fragment.DetalheLivroFragment;
import com.gcfhfbj.casadocodigo.casadocodigo.fragment.ListaLivrosFragment;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;

public class MainActivity extends AppCompatActivity implements LivrosDelegate, FragmentManager.OnBackStackChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_principal, new ListaLivrosFragment());
        transaction.commit();

        //Habilitar botão voltar no topo
        getSupportFragmentManager().addOnBackStackChangedListener(this);
        mostrarVoltar();
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
