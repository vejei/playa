package io.github.zeleven.playa.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.github.zeleven.playa.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application application();
}
