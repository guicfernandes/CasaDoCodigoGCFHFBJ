package com.gcfhfbj.casadocodigo.casadocodigo;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Carrinho;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Guilherme on 07/09/2017.
 */

@Module
public class CasaDoCodigoModule {
    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }
}
