package io.github.zeleven.playa.ui.module.main.navigation;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.playa.data.model.BaseResponse;
import io.github.zeleven.playa.data.model.NavCategory;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NavigationPresenter extends BasePresenter<NavigationContract.View>
        implements NavigationContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Override
    public void detachView() {
        super.detachView();
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Inject
    public NavigationPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void getNavCategories() {
        Observable<BaseResponse<List<NavCategory>>> observable = dataManager.getNavCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<List<NavCategory>>, List<NavCategory>>() {
                    @Override
                    public List<NavCategory> apply(@NonNull BaseResponse<List<NavCategory>> response)
                            throws Exception {
                        return response.getData();
                    }
                }).subscribeWith(new Observer<List<NavCategory>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<NavCategory> categories) {
                getView().showNavCategories(categories);
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
