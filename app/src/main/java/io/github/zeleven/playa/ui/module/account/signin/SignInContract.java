package io.github.zeleven.playa.ui.module.account.signin;

import io.github.zeleven.playa.ui.base.BaseContract;

public interface SignInContract {
    interface View extends BaseContract.View {
        void usernameEmpty(boolean isEmpty);
        void passwordEmpty(boolean isEmpty);
        void loginSuccessful(String username);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void signIn(String username, String password);
    }
}
