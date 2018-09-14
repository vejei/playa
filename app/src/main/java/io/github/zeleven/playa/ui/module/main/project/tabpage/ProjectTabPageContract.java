package io.github.zeleven.playa.ui.module.main.project.tabpage;

import java.util.List;

import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface ProjectTabPageContract {
    interface View extends BaseContract.View {
        void showProjectArticles(int page, List<Article> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getProjectArticles(int page, int cid);
    }
}
