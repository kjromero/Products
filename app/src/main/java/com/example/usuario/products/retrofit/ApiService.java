package com.example.usuario.products.retrofit;

import com.example.usuario.products.model.ResponseProducts;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiService {

    /**
     * Obtiene la lista de productos
     *
     * @return
     */
    @GET("GetProductList")
    Call<ResponseProducts> getProducts();
}
