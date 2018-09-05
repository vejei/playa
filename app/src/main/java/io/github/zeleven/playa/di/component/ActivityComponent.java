package io.github.zeleven.playa.di.component;

import dagger.Component;
import io.github.zeleven.playa.di.module.ActivityModule;
import io.github.zeleven.playa.di.scope.ActivityScope;
import io.github.zeleven.playa.ui.module.main.MainActivity;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
