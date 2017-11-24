package com.example.usuario.products.dagger;


import android.content.Context;

import com.example.usuario.products.retrofit.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetModule {

    /**
     * El contexto de la aplicación
     */
    private Context context;

    /**
     * Constructor que inicializa el contexto de la aplicación
     * @param context
     */
    public NetModule(Context context)
    {
        this.context = context;
    }

    /**
     * Provee el servicio para hacer peticiones HTTP al API
     * @return
     */
    @Provides
    @Singleton
    ApiService provideApiService()
    {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dev.controlbox.net:86/SampleService/Service1.svc/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        ApiService service = retrofit.create(ApiService.class);
        return service;
    }
}
