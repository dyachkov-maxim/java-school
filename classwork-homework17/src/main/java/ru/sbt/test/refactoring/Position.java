package ru.sbt.test.refactoring;

public class Position {
    private static final int borderValue = 5;
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void incPosX() {
        this.x++;
    }

    public void incPosY() {
        this.y++;
    }

    public void decPosX() {
        this.x--;
    }

    public void decPosY() {
        this.y--;
    }

    public boolean isNotValid() {
        return (getX() > borderValue || getY() > borderValue);
    }
}
