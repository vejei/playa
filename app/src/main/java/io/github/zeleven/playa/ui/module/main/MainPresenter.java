package io.github.zeleven.playa.ui.module.main;

import javax.inject.Inject;

import io.github.zeleven.playa.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View>
        implements MainContract.Presenter {

    @Inject
    public MainPresenter() {
    }
}
