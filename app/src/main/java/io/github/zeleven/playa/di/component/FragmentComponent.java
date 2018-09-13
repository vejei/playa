package io.github.zeleven.playa.di.component;

import dagger.Component;
import io.github.zeleven.playa.di.module.FragmentModule;
import io.github.zeleven.playa.di.scope.FragmentScope;
import io.github.zeleven.playa.ui.module.account.signin.SignInFragment;
import io.github.zeleven.playa.ui.module.account.signup.SignUpFragment;
import io.github.zeleven.playa.ui.module.main.hierarchy.HierarchyFragment;
import io.github.zeleven.playa.ui.module.main.hierarchy.detail.tabpage.HierarchyTabPageFragment;
import io.github.zeleven.playa.ui.module.main.home.HomeFragment;
import io.github.zeleven.playa.ui.module.main.mine.MineFragment;
import io.github.zeleven.playa.ui.module.main.navigation.NavigationFragment;
import io.github.zeleven.playa.ui.module.main.project.ProjectFragment;
import io.github.zeleven.playa.ui.module.main.project.tabpage.ProjectTabPageFragment;
import io.github.zeleven.playa.ui.module.search.result.SearchResultFragment;
import io.github.zeleven.playa.ui.module.search.suggest.SearchSuggestFragment;

@FragmentScope
@Component(modules = FragmentModule.class, dependencies = ApplicationComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment homeFragment);
    void inject(ProjectFragment projectFragment);
    void inject(ProjectTabPageFragment projectTabPageFragment);
    void inject(HierarchyFragment hierarchyFragment);
    void inject(HierarchyTabPageFragment hierarchyTabPageFragment);
    void inject(NavigationFragment navigationFragment);
    void inject(MineFragment mineFragment);
    void inject(SearchResultFragment searchResultFragment);
    void inject(SearchSuggestFragment searchSuggestFragment);
    void inject(SignInFragment signInFragment);
    void inject(SignUpFragment signUpFragment);
}
