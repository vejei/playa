package io.github.zeleven.playa.ui.module.main.project;

import java.util.List;

import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface ProjectContract {
    interface View extends BaseContract.View {
        void createTabs(List<Category> categories);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getProjectCategories();
    }
}
