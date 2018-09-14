package io.github.zeleven.playa.ui.module.main.hierarchy.detail.tabpage;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.data.model.ArticleListResponse;
import io.github.zeleven.playa.data.model.BaseResponse;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HierarchyTabPagePresenter extends BasePresenter<HierarchyTabPageContract.View>
        implements HierarchyTabPageContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public HierarchyTabPagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getHierarchyArticles(final int page, int cid) {
        Observable<BaseResponse<ArticleListResponse>> observable =
                dataManager.getHierarchyArticles(page, cid);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<ArticleListResponse>, List<Article>>() {
                    @Override
                    public List<Article> apply(@NonNull BaseResponse<ArticleListResponse> response)
                            throws Exception {
                        return response.getData().getDatas();
                    }
                }).subscribeWith(new Observer<List<Article>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<Article> articles) {
                getView().showHierarchyArticles(page, articles);
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}
