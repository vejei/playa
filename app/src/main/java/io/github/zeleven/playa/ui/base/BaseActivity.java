package io.github.zeleven.playa.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.zeleven.playa.Playa;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.di.component.ActivityComponent;
import io.github.zeleven.playa.di.component.DaggerActivityComponent;
import io.github.zeleven.playa.ui.module.main.MainActivity;
import io.github.zeleven.playa.utils.NetworkUtils;

public abstract class BaseActivity<P extends BaseContract.Presenter>
        extends AppCompatActivity implements BaseContract.View {
    @Inject protected P presenter;

    @Nullable @BindView(R.id.toolbar) protected Toolbar toolbar;
    @Nullable @BindView(R.id.appbar_layout) protected AppBarLayout appbarLayout;

    protected ActivityComponent activityComponent;

    public abstract int getLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (this instanceof MainActivity) {
            setTheme(R.style.AppTheme);
        }
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);

        activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(((Playa) getApplication()).getApplicationComponent())
                .build();

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkAvailable(this);
    }

    @Override
    public void showError(String message) {
        Log.e(getClass().getName(), message);
    }

    public void enableAppBarElevation(boolean enable) {
        if (appbarLayout != null) {
            appbarLayout.setElevation(enable ? getResources().getDimension(R.dimen.four_dp) : 0.0f);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
