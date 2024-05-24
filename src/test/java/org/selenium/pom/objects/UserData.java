package org.selenium.pom.objects;

public class UserData {
    private String login;
    private String password;
    private String email;

    public UserData() {}

    public UserData(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserData(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public UserData setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserData setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserData setEmail(String email) {
        this.email = email;
        return this;
    }
}
