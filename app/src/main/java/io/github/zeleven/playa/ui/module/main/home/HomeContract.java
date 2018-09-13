package io.github.zeleven.playa.ui.module.main.home;

import java.util.List;

import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.data.model.Banner;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface HomeContract {
    interface View extends BaseContract.View {
        void showArticles(int page, List<Article> data);
        void showBannerData(List<Banner> data);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getArticles(int page);
        void getBannerData();
    }
}
