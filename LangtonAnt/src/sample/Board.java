/**
 * @file Board.java
 * @brief klasa tablicy dwuwymiarowej
 *
 * @author Damian Charkiewicz and Jakub Glinka
 * @date 06.06.2018r
 *
 */

package sample;

public class Board {

    /**
     * @param value int[][] tablica przechowująca liczbe wejść mrówek na dane pole
     * @param x int maksymalny x pola
     * @param y int maksymalny y pola
     */

    private int[][] value;
    private int x;
    private int y;

    public Board(int x, int y) {
        this.value = new int[x][y];
        this.x = x;
        this.y = y;
    }

    public int getValue(int x, int y) {
        return value[x][y];
    }

    public void setValue(int x, int y, int value) {
        this.value[x][y] = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /** Metoda wyzerowująca wartości pól*/
    public void reset() {
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                value[x][y] = 0;
    }
}
