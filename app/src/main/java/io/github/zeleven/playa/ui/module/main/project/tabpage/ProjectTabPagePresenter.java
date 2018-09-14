package io.github.zeleven.playa.ui.module.main.project.tabpage;

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

public class ProjectTabPagePresenter extends BasePresenter<ProjectTabPageContract.View>
        implements ProjectTabPageContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public ProjectTabPagePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Override
    public void getProjectArticles(final int page, int cid) {
        Observable<BaseResponse<ArticleListResponse>> observable =
                dataManager.getProjectArticles(page, cid);
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
                getView().showProjectArticles(page, articles);
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
