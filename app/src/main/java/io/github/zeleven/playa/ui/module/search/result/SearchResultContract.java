package io.github.zeleven.playa.ui.module.search.result;

import java.util.List;

import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface SearchResultContract {
    interface View extends BaseContract.View {
        void showSearchResult(int page, List<Article> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void saveSearchHistory(String keyword);
        void search(int page, String keyword);
    }
}
