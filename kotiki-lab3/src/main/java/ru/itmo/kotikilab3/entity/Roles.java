package ru.itmo.kotikilab3.entity;

public enum Roles {
    USER("USER"),
    ADMIN("ADMIN");

    private String title;

    Roles(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title;
    }
}
