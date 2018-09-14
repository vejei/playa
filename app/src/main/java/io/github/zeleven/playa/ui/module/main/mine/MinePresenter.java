package io.github.zeleven.playa.ui.module.main.mine;

import javax.inject.Inject;

import io.github.zeleven.playa.data.model.User;
import io.github.zeleven.playa.data.source.DataManager;
import io.github.zeleven.playa.ui.base.BasePresenter;

public class MinePresenter extends BasePresenter<MineContract.View>
        implements MineContract.Presenter {
    private DataManager dataManager;

    @Inject
    public MinePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public boolean isLogin() {
        return dataManager.isLogin();
    }

    @Override
    public User getLoggedInUser() {
        return dataManager.getLoggedInUser();
    }

    @Override
    public void logout() {
        dataManager.deleteLoggedInUser();
    }
}
