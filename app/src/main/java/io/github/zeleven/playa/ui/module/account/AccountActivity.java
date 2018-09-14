package io.github.zeleven.playa.ui.module.account;

import android.os.Bundle;
import android.support.annotation.Nullable;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseActivity;
import io.github.zeleven.playa.ui.module.account.signin.SignInFragment;

public class AccountActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_account;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SignInFragment())
                .commit();
    }
}
