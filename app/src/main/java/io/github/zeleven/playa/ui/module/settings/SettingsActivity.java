package io.github.zeleven.playa.ui.module.settings;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseActivity;

public class SettingsActivity extends BaseActivity {
    @Override
    public int getLayout() {
        return R.layout.activity_settings;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.toolbar_title_setting);
        }

        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, new SettingsFragment())
                .commit();
    }
}
