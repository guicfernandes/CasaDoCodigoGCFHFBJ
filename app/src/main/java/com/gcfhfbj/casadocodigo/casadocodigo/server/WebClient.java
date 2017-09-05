package com.gcfhfbj.casadocodigo.casadocodigo.server;

import com.gcfhfbj.casadocodigo.casadocodigo.event.LivroEvent;
import com.gcfhfbj.casadocodigo.casadocodigo.converter.LivroServiceConverterFactory;
import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by android7281 on 05/09/17.
 */

public class WebClient {

    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";

    /*
    private LivrosDelegate delegate;
    public WebClient(LivrosDelegate delegate) {
        this.delegate = delegate;
    }
    */

    public WebClient() {

    }

    public void getLivros() {
        Retrofit client = new Retrofit.Builder().
                baseUrl(SERVER_URL).
                addConverterFactory(new LivroServiceConverterFactory()).
                build();

        LivrosService service = client.create(LivrosService.class);

        Call<List<Livro>> call = service.listaLivros();

        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                //delegate.lidaComSucesso(response.body());
                EventBus.getDefault().post(new LivroEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                //delegate.lidaComErro(t);
                EventBus.getDefault().post(t);
            }
        });
    }
}
