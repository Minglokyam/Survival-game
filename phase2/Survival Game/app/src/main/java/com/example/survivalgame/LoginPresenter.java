package com.example.survivalgame;

public class LoginPresenter implements LoginPresenterInterface {

    public void onRegisterSuccess(){}
    public void onUserNotExists(){}
    public void onCredentialEmpty(){}
    public void launchRunningGame(String name, User user){};
    public void launchPongGame(String name, User user){};
    public void launchDodgeGame(String name, User user){};
    public void onWrongCredential(){}

    public LoginPresenter(LoginView loginView, LoginInteractor loginInteractor) {
    }

    public void validateRegisterCredentials(String toString, String toString1) {
    }

    public void validateLoginCredentials(String toString, String toString1) {
    }
}
