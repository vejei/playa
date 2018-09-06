package io.github.zeleven.playa.ui.module.browser;

import io.github.zeleven.playa.ui.base.BaseContract;

public interface BrowserContract {
    interface View extends BaseContract.View {
        void openShareDialog();
        void copyTextToClipboard();
        void openBrowser();
    }

    interface Presenter extends BaseContract.Presenter<View> {}
}
