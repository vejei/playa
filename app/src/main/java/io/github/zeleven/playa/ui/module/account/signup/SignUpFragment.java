package io.github.zeleven.playa.ui.module.account.signup;

import android.content.Context;

import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseFragment;

public class SignUpFragment extends BaseFragment<SignUpPresenter> implements SignUpContract.View {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_sign_up;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);
    }
}
