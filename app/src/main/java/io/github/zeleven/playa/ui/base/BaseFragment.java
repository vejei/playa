package io.github.zeleven.playa.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import butterknife.ButterKnife;
import io.github.zeleven.playa.Playa;
import io.github.zeleven.playa.di.component.DaggerFragmentComponent;
import io.github.zeleven.playa.di.component.FragmentComponent;

public abstract class BaseFragment<P extends BaseContract.Presenter> extends Fragment
        implements BaseContract.View {
    @Inject protected P presenter;

    protected Context context;
    protected FragmentComponent fragmentComponent;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(((Playa) (context.getApplicationContext()))
                        .getApplicationComponent())
                .build();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this, view);
        onFragmentViewCreated();
        return view;
    }

    public abstract int getLayout();

    public void onFragmentViewCreated() {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @Override
    public boolean isNetworkConnected() {
        return ((BaseActivity) context).isNetworkConnected();
    }

    @Override
    public void showError(String message) {
        ((BaseActivity) context).showError(message);
    }
}
