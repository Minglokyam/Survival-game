package com.example.survivalgame;

public class LoginPresenter implements LoginPresenterInterface {

    public void onRegisterSuccess(){}
    public void onUserNotExists(){}
    public void onCredentialEmpty(){}
    public void onLoginSuccess(){}
    public void onWrongCredential(){}

    public LoginPresenter(LoginView loginview, LoginInteractor loginInteractor) {
    }

    public void validateRegisterCredentials(String toString, String toString1) {
    }

    public void validateLoginCredentials(String toString, String toString1) {
    }
}
