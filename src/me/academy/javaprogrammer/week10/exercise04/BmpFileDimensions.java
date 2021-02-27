package me.academy.javaprogrammer.week10.exercise04;

final class BmpFileDimensions {
    private final int width;
    private final int height;

    BmpFileDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return width + " x " + height;
    }
}
