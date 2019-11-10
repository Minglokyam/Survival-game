package com.example.survivalgame;

public class LoginInteractor {

    public void register(

            String username, String password, LoginPresenterInterface loginPresenterInterface) {
        IOManager.loadFile();
        if (!(username.trim().equals("") || password.trim().equals(""))) {
            if (!UserManager.userExists(username)) {
                User newUser = new User(username, password);
                UserManager.addUser(username, newUser);
                IOManager.saveFile();
                loginPresenterInterface.onRegisterSuccess();
            } else {
                loginPresenterInterface.onUserAlreadyExists();
            }
        } else {
            loginPresenterInterface.onCredentialEmpty();
        }
    }

    public void login(

            String username, String password, LoginPresenterInterface loginPresenterInterface) {
        IOManager.loadFile();
        if (!(username.trim().equals("") || password.trim().equals(""))) {
            System.out.println(UserManager.userExists(username));
            if (UserManager.userExists(username)) {
                User temp = UserManager.getUser(username);
                if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
                    onLoginSuccess(username, temp, loginPresenterInterface);
                } else {
                    loginPresenterInterface.onWrongCredential();
                }
            } else {
                loginPresenterInterface.onUserNotExists();
            }
        } else {
            loginPresenterInterface.onCredentialEmpty();
        }
    }

    private void onLoginSuccess(String name, User user, LoginPresenterInterface loginPresenterInterface) {
        int gameStage = user.getGameStage();
        if (gameStage == User.RUNNING) {
            loginPresenterInterface.launchRunningGame(name, user);
        } else if (gameStage == User.PONG) {
            loginPresenterInterface.launchPongGame(name, user);

        } else if (gameStage == User.DODGE) {
            loginPresenterInterface.launchDodgeGame(name, user);
        }
    }
}
