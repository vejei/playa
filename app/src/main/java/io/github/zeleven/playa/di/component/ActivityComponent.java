package io.github.zeleven.playa.di.component;

import dagger.Component;
import io.github.zeleven.playa.di.module.ActivityModule;
import io.github.zeleven.playa.di.scope.ActivityScope;

@ActivityScope
@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {
}
