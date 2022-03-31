package entity;

public enum Colors {
    Black("BLack"),
    White("White"),
    Blue("Blue"),
    Grey("Grey"),
    Pink("Pink"),
    Rainbow("Rainbow");

    private String title;

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