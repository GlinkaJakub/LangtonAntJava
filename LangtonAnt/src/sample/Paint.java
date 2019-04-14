/**
 * @file Paint.java
 * @brief klasa pola do pomalowania
 *
 * @author Damian Charkiewicz and Jakub Glinka
 * @date 06.06.2018r
 *
 */

package sample;

import javafx.scene.paint.Color;

public class Paint {
    int x;
    int y;
    Color color = Color.WHITE;

    /**
     *
     * @param x int współrzędna x pola do zamalowania
     * @param y int współrzędna y pola do zamalowania
     * @param color Color kolor na jaki ma zostać pomalowane pole
     */
    public Paint(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Paint{" +
                "x=" + x +
                ", y=" + y +
                ", color=" + color +
                '}';
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
