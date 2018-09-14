package io.github.zeleven.playa.ui.module.search.suggest;

import java.util.List;

import io.github.zeleven.playa.data.model.HotKey;
import io.github.zeleven.playa.data.model.SearchHistory;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface SearchSuggestContract {
    interface View extends BaseContract.View {
        void showHotKey(List<HotKey> data);
        void showSearchHistory(List<SearchHistory> data);
        void updateHistoryLayout();
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getHotKey();
        void getSearchHistory();
        void clearSearchHistory();
    }
}
