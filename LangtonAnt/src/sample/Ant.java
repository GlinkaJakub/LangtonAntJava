/**
 * @file Ant.java
 * @brief klasa Ant przechowuje parametry mrówki
 *
 * @author Damian Charkiewicz and Jakub Glinka
 * @date 06.06.2018r
 *
 */

package sample;

public class Ant {
    private int x;
    private int y;
    private int direction = 0;

    /**
     *
     *
     * @param x współrzędna x mrówki
     * @param y współrzędna y mrówki
     */

    public Ant(int x, int y) {
        this.x = x;
        this.y = y;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

