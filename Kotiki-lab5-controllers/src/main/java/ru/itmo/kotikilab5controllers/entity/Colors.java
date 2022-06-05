package ru.itmo.kotikilab5controllers.entity;

public enum Colors {
    BLACK("BLACK"),
    WHITE("WHITE"),
    BLUE("BLUE"),
    GRAY("GREY"),
    PINK("PINK"),
    RAINBOW("RAINBOW");

    private String title;
    private Colors colors;

    Colors(String title) {
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