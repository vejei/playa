package io.github.zeleven.playa.ui.module.main.hierarchy.detail.tabpage;

import java.util.List;

import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface HierarchyTabPageContract {
    interface View extends BaseContract.View {
        void showHierarchyArticles(int page, List<Article> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getHierarchyArticles(int page, int cid);
    }
}
