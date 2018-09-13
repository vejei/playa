package io.github.zeleven.playa.ui.module.main.home;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.playa.data.model.Article;
import io.github.zeleven.playa.data.model.ArticleListResponse;
import io.github.zeleven.playa.data.model.Banner;
import io.github.zeleven.playa.data.model.BaseResponse;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeContract.View>
        implements HomeContract.Presenter {
    private DataManager dataManager;
    private CompositeDisposable disposable = new CompositeDisposable();

    @Inject
    public HomePresenter(DataManager dataManager) {
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
    public void getArticles(final int page) {
        Observable<BaseResponse<ArticleListResponse>> observable = dataManager.getArticles(page);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<ArticleListResponse>, List<Article>>() {
                    @Override
                    public List<Article> apply(
                            @NonNull BaseResponse<ArticleListResponse> response)
                            throws Exception {
                        return response.getData().getDatas();
                    }
                }).subscribeWith(new Observer<List<Article>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull List<Article> articles) {
                getView().showArticles(page, articles);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                getView().showError(e.getMessage());
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void getBannerData() {
        Observable<BaseResponse<List<Banner>>> observable = dataManager.getBannerData();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<List<Banner>>, List<Banner>>() {
                    @Override
                    public List<Banner> apply(@NonNull BaseResponse<List<Banner>> response)
                            throws Exception {
                        return response.getData();
                    }
                }).subscribeWith(new Observer<List<Banner>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable.add(d);
            }

            @Override
            public void onNext(@NonNull List<Banner> banners) {
                getView().showBannerData(banners);
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
