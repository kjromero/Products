package com.example.usuario.products;

import android.app.Application;

import com.example.usuario.products.dagger.ApplicationComponent;
import com.example.usuario.products.dagger.DaggerApplicationComponent;
import com.example.usuario.products.dagger.NetModule;


public class MyApplication extends Application {

    /**
     * Componente Dagger que maneja las dependencias de la aplicación.
     */
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializa el componente Dagger que maneja las dependencias de la aplicación.
        applicationComponent = DaggerApplicationComponent.builder()
                .netModule(new NetModule(this))
                .build();
    }

    /**
     * Retorna el componente Dagger para el manejo de las dependencias de la aplicación.
     *
     * @return El componente Dagger para la aplicación.
     */
    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
