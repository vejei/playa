package io.github.zeleven.playa.di.component;

import dagger.Component;
import io.github.zeleven.playa.di.module.FragmentModule;
import io.github.zeleven.playa.di.scope.FragmentScope;
import io.github.zeleven.playa.ui.module.main.home.HomeFragment;
import io.github.zeleven.playa.ui.module.main.project.ProjectFragment;
import io.github.zeleven.playa.ui.module.main.project.tabpage.ProjectTabPageFragment;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);
    void inject(ProjectFragment projectFragment);
    void inject(ProjectTabPageFragment projectTabPageFragment);
}
