package com.example.survivalgame;

public class LoginPresenter implements LoginPresenterInterface {
    private LoginView loginView;

    LoginPresenter(LoginView loginView){
        this.loginView = loginView;
    }

    public void onRegisterSuccess(){}
    public void onUserNotExists(){}
    public void onCredentialEmpty(){}
    public void launchRunningGame(String name, User user){
        loginView.launchRunningGame(name, user);
    }
    public void launchPongGame(String name, User user){
        loginView.launchPongGame(name, user);
    }
    public void launchDodgeGame(String name, User user){
        loginView.launchDodgeGame(name, user);
    }
    public void onWrongCredential(){}

    public LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
    }

    public void validateRegisterCredentials(String toString, String toString1) {
    }

    public void validateLoginCredentials(String toString, String toString1) {
    }
}
