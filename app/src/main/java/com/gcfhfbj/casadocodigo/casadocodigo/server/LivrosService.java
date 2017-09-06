package com.gcfhfbj.casadocodigo.casadocodigo.server;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android7281 on 05/09/17.
 */

public interface LivrosService {

    @GET("listarLivros")
    Call<List<Livro>> listaLivros(@Query("indicePrimeiroLivro") int indicePrimeiroLivro, @Query("qtdLivros") int qtdLivros);
}
