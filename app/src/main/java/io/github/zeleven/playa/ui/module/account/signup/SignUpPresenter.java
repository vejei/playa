package io.github.zeleven.playa.ui.module.account.signup;

import javax.inject.Inject;

import io.github.zeleven.playa.data.model.BaseResponse;
import io.github.zeleven.playa.data.model.LoginResponse;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.ui.base.BasePresenter;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class SignUpPresenter extends BasePresenter<SignUpContract.View>
        implements SignUpContract.Presenter {
    private DataManager dataManager;
    private Disposable disposable;

    @Inject
    public SignUpPresenter(DataManager dataManager) {
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
    public void signUp(String username, String password, String repassword) {
        Observable<BaseResponse<LoginResponse>> observable =
                dataManager.signup(username, password, repassword);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(new Function<BaseResponse<LoginResponse>, LoginResponse>() {
                    @Override
                    public LoginResponse apply(@NonNull BaseResponse<LoginResponse> response)
                            throws Exception {
                        return response.getData();
                    }
                })
                .subscribeWith(new Observer<LoginResponse>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(@NonNull LoginResponse loginResponse) {

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
