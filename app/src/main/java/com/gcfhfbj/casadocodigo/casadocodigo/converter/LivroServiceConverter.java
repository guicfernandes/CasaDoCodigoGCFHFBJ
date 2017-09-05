package com.gcfhfbj.casadocodigo.casadocodigo.converter;

/**
 * Created by android7281 on 05/09/17.
 */
import java.io.IOException;
import java.util.List;

import com.gcfhfbj.casadocodigo.casadocodigo.model.Livro;
import okhttp3.ResponseBody;
import retrofit2.Converter;

public class LivroServiceConverter implements Converter<ResponseBody, List<Livro>> {

    @Override
    public List<Livro> convert(ResponseBody value) throws IOException {

        String json = value.string();
        LivroConverter livroConverter = new LivroConverter();

        List<Livro> livros = livroConverter.fromJson(json);

        return livros;
    }
}