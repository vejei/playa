package io.github.zeleven.playa.ui.module.main.project;

import java.util.List;

import javax.inject.Inject;

import io.github.zeleven.playa.data.model.BaseResponse;
import io.github.zeleven.playa.data.model.Category;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ProjectPresenter extends BasePresenter<ProjectContract.View>
        implements ProjectContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public ProjectPresenter(DataManager dataManager) {
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
    public void getProjectCategories() {
        Observable<BaseResponse<List<Category>>> observable = dataManager.getProjectCategories();
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<List<Category>>, List<Category>>() {
                    @Override
                    public List<Category> apply(@NonNull BaseResponse<List<Category>> response)
                            throws Exception {
                        return response.getData();
                    }
                }).subscribeWith(new Observer<List<Category>>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull List<Category> categories) {
                getView().createTabs(categories);
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
