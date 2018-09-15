package io.github.zeleven.playa.eventbus;

public class LoginEvent {
    private boolean isLogin;
    private String loginUsername;

    public LoginEvent(boolean isLogin, String loginUsername) {
        this.isLogin = isLogin;
        this.loginUsername = loginUsername;
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public String getLoginUsername() {
        return loginUsername;
    }

    public void setLoginUsername(String loginUsername) {
        this.loginUsername = loginUsername;
    }
}
