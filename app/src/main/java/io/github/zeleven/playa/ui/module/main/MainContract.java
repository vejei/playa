package io.github.zeleven.playa.ui.module.main;

import io.github.zeleven.playa.ui.base.BaseContract;

public interface MainContract {
    interface View extends BaseContract.View {}

    interface Presenter extends BaseContract.Presenter<View> {}
}
