package com.gcfhfbj.casadocodigo.casadocodigo;

import android.app.Application;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Carrinho;

/**
 * Created by android7281 on 06/09/17.
 */

public class CasaDoCodigoApplication extends Application {

    private CasaDoCodigoComponent component;

     @Override
     public void onCreate() {
        super.onCreate();

        component = DaggerCasaDoCodigoComponent.builder().build();
     }

    public CasaDoCodigoComponent getComponent() {
        return component;
    }
}
