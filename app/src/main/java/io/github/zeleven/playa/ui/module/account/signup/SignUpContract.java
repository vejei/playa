package io.github.zeleven.playa.ui.module.account.signup;

import io.github.zeleven.playa.ui.base.BaseContract;

public interface SignUpContract {
    interface View extends BaseContract.View {}

    interface Presenter extends BaseContract.Presenter<View> {
        void signUp(String username, String password, String repassword);
    }
}
