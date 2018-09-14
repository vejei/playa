package io.github.zeleven.playa.ui.module.main.hierarchy;

import java.util.List;

import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface HierarchyContract {
    interface View extends BaseContract.View {
        void showHierarchyCategories(List<Category> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getHierarchyCategories();
    }
}
