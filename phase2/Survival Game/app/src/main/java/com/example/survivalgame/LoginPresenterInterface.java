package com.example.survivalgame;

public interface LoginPresenterInterface {
    void onRegisterSuccess();
    void onUserNotExists();
    void onCredentialEmpty();
    void onLoginSuccess();
    void onWrongCredential();
}
