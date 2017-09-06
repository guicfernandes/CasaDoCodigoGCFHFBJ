package com.gcfhfbj.casadocodigo.casadocodigo;

import android.app.Application;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Carrinho;

/**
 * Created by android7281 on 06/09/17.
 */

public class CasaDoCodigoApplication extends Application {

    private Carrinho carrinho = new Carrinho();

    public Carrinho getCarrinho() {
        return carrinho;
    }
}
