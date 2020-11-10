package com.accenture.itfactory.entity;

public class User {
    private String name;
    private String role;
    private String login;

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
