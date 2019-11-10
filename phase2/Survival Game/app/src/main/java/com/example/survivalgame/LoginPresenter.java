package com.example.survivalgame;

public class LoginPresenter implements LoginPresenterInterface {

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }

    public void onRegisterSuccess() {
        if (loginView != null) {
            loginView.setRegisterSuccess();
        }
    }

    public void onUserNotExists() {
        if (loginView != null) {
            loginView.setUserNotExistError();
        }
    }

    @Override
    public void onUserAlreadyExists() {
        if (loginView != null) {
            loginView.setUserAlreadyExistError();
        }
    }

    public void onCredentialEmpty() {
        if (loginView != null) {
            loginView.setEmptyInputError();
        }
    }

    public void onWrongCredential() {
        if (loginView != null) {
            loginView.setCredentialsError();
        }
    }

    public void launchRunningGame(String name, User user) {
        loginView.launchRunningGame(name, user);
    }

    public void launchPongGame(String name, User user) {
        loginView.launchPongGame(name, user);
    }

    public void launchDodgeGame(String name, User user) {
        loginView.launchDodgeGame(name, user);
    }


    public void validateRegisterCredentials(String username, String password) {
        loginInteractor.register(username, password, this);
    }

    public void validateLoginCredentials(String username, String password) {
        loginInteractor.login(username, password, this);
    }
}
