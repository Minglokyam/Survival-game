package com.example.survivalgame;

class LoginInteractor {
    void register(String username, String password, LoginListener loginListener) {
        IOManager.loadFile();
        if (!(username.trim().equals("") || password.trim().equals(""))) {
            if (!UserManager.userExists(username)) {
                User newUser = new User(username, password);
                UserManager.addUser(username, newUser);
                IOManager.saveFile();
                loginListener.onRegisterSuccess();
            } else {
                loginListener.onUserAlreadyExists();
            }
        } else {
            loginListener.onCredentialEmpty();
        }
    }

    void login(String username, String password, LoginListener loginListener) {
        IOManager.loadFile();
        if (!(username.trim().equals("") || password.trim().equals(""))) {
            System.out.println(UserManager.userExists(username));
            if (UserManager.userExists(username)) {
                User temp = UserManager.getUser(username);
                if (temp.getUsername().equals(username) && temp.getPassword().equals(password)) {
                    onLoginSuccess(username, temp, loginListener);
                } else {
                    loginListener.onWrongCredential();
                }
            } else {
                loginListener.onUserNotExists();
            }
        } else {
            loginListener.onCredentialEmpty();
        }
    }

    private void onLoginSuccess(String name, User user, LoginListener loginListener) {
        int gameStage = user.getGameStage();
        if (gameStage == User.RUNNING) {
            loginListener.launchRunningGame(name, user);
        } else if (gameStage == User.PONG) {
            loginListener.launchPongGame(name, user);

        } else if (gameStage == User.DODGE) {
            loginListener.launchDodgeGame(name, user);
        }
    }
}
