package io.github.zeleven.playa.di.module;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.zeleven.playa.Playa;

@Module
public class ApplicationModule {
    private Playa application;

    public ApplicationModule(Playa application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }
}
