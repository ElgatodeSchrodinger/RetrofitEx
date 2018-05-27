package com.javtex.retrofitex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CombuyClient {
    @GET("localnegocio/{id}")
    public Call<CombuyLocal> getLocal(@Path("id") int id);

}
