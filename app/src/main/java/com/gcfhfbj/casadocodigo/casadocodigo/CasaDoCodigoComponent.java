package com.gcfhfbj.casadocodigo.casadocodigo;

import com.gcfhfbj.casadocodigo.casadocodigo.activity.CarrinhoActivity;
import com.gcfhfbj.casadocodigo.casadocodigo.activity.MainActivity;
import com.gcfhfbj.casadocodigo.casadocodigo.fragment.DetalheLivroFragment;
import com.gcfhfbj.casadocodigo.casadocodigo.fragment.ListaLivrosFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
* Created by Guilherme on 07/09/2017.
*/

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {

    void inject(DetalheLivroFragment fragment);

    void inject(CarrinhoActivity activity);

    void inject(ListaLivrosFragment fragment);

    void inject(MainActivity activity);
}