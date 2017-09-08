package com.gcfhfbj.casadocodigo.casadocodigo;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Carrinho;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

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

     @Provides
     @Singleton
     FirebaseRemoteConfig provideRemoteConfig(FirebaseRemoteConfigSettings settings) {
         FirebaseRemoteConfig config = FirebaseRemoteConfig.getInstance();
         config.setDefaults(R.xml.remote_config);
         config.setConfigSettings(settings);

         return config;
     }

     @Provides
     FirebaseRemoteConfigSettings provideConfigSettings() {
         FirebaseRemoteConfigSettings settings = new FirebaseRemoteConfigSettings.Builder().
                 setDeveloperModeEnabled(true).build();
         return settings;
     }


 }