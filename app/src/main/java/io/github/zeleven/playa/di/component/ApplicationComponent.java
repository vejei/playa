package io.github.zeleven.playa.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.data.source.local.DatabaseHelper;
import io.github.zeleven.playa.di.module.ApplicationModule;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    Application application();
    DataManager dataManager();
    DatabaseHelper databaseHelper();
}
