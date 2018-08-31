package io.github.zeleven.playa.di.component;

import dagger.Component;
import io.github.zeleven.playa.di.module.FragmentModule;
import io.github.zeleven.playa.di.scope.FragmentScope;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
}
