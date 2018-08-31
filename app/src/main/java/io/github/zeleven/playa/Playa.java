package io.github.zeleven.playa;

import android.app.Application;

import io.github.zeleven.playa.di.component.ApplicationComponent;
import io.github.zeleven.playa.di.component.DaggerApplicationComponent;
import io.github.zeleven.playa.di.module.ApplicationModule;

public class Playa extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
