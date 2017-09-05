package com.gcfhfbj.casadocodigo.casadocodigo.converter;

/**
 * Created by android7281 on 05/09/17.
 */
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

public class ItemServiceRequestConverter implements Converter<String, RequestBody> {

    @Override
    public RequestBody convert(String json) throws IOException {
        return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
    }
}
