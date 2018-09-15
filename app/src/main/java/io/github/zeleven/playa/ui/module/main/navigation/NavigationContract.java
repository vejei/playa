package io.github.zeleven.playa.ui.module.main.navigation;

import java.util.List;

import io.github.zeleven.playa.data.model.NavCategory;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface NavigationContract {
    interface View extends BaseContract.View {
        void showNavCategories(List<NavCategory> categories);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getNavCategories();
    }
}
