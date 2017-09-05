package com.gcfhfbj.casadocodigo.casadocodigo.event;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;

import java.util.List;

/**
 * Created by android7281 on 05/09/17.
 */

public class LivroEvent {

    private final List<Livro> livros;

    public LivroEvent(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
