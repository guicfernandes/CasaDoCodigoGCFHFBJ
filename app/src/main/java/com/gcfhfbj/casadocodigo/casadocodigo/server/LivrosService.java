package com.gcfhfbj.casadocodigo.casadocodigo.server;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by android7281 on 05/09/17.
 */

public interface LivrosService {

    @GET("listarLivros?indicePrimeiroLivro=0&qtdLivros=20")
    Call<List<Livro>> listaLivros();
}
