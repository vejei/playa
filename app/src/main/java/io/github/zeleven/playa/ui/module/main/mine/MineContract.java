package io.github.zeleven.playa.ui.module.main.mine;

import io.github.zeleven.playa.data.model.User;
import io.github.zeleven.playa.ui.base.BaseContract;

public interface MineContract {
    interface View extends BaseContract.View {
    }

    interface Presenter extends BaseContract.Presenter<View> {
        boolean isLogin();
        User getLoggedInUser();
        void logout();
    }
}
