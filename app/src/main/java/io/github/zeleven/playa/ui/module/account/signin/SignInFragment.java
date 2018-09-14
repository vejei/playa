package io.github.zeleven.playa.ui.module.account.signin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import io.github.zeleven.playa.R;
import io.github.zeleven.playa.ui.base.BaseFragment;
import io.github.zeleven.playa.ui.module.account.signup.SignUpFragment;
import io.github.zeleven.playa.ui.module.main.MainActivity;

public class SignInFragment extends BaseFragment<SignInPresenter> implements SignInContract.View {
    @BindView(R.id.username_input_layout) TextInputLayout usernameInputLayout;
    @BindView(R.id.password_input_layout) TextInputLayout passwordInputLayout;
    @BindView(R.id.username_input) TextInputEditText usernameInput;
    @BindView(R.id.password_input) TextInputEditText passwordInput;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentComponent.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_sign_in;
    }

    @Override
    public void onFragmentViewCreated() {
        super.onFragmentViewCreated();

        if (presenter == null) {
            return;
        }
        presenter.attachView(this);
    }

    @OnClick(R.id.sign_in_button)
    public void signIn() {
        presenter.signIn(usernameInput.getText().toString(), passwordInput.getText().toString());
    }

    @OnClick(R.id.link_signup)
    public void switchToSignup() {
        ((AppCompatActivity) context).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, new SignUpFragment())
                .commit();
    }

    @Override
    public void usernameEmpty(boolean isEmpty) {
        if (isEmpty) {
            usernameInputLayout.setErrorEnabled(true);
            usernameInputLayout.setError(getString(R.string.username_can_not_be_empty));
        } else {
            usernameInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void passwordEmpty(boolean isEmpty) {
        if (isEmpty) {
            passwordInputLayout.setErrorEnabled(true);
            passwordInputLayout.setError(getString(R.string.password_can_not_be_empty));
        } else {
            passwordInputLayout.setErrorEnabled(false);
        }
    }

    @Override
    public void loginSuccessful() {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
        ((AppCompatActivity) context).finish();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
