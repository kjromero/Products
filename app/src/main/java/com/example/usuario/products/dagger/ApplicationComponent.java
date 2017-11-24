package com.example.usuario.products.dagger;

import com.example.usuario.products.MainActivity;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {NetModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);
}
