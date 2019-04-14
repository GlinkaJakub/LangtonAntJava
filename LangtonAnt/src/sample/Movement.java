/**
 * @file Movement.java
 * @brief klasa implementuje metodę wykonującą ruch mrówki
 *
 * @author Damian Charkiewicz and Jakub Glinka
 * @date 06.06.2018r
 *
 */

package sample;

import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Movement {

    /**
     * @param ants ArrayList<Ant> lista mrówek
     * @param movementPattern String zachowanie mrówki
     */
    private ArrayList<Ant> ants;
    static private String movementPattern = "LR";
    static private Board board;
    static private boolean run = false;
    private boolean update = true;

    public boolean isUpdate() {
        return update;
    }

    public void clear(){
        board.reset();
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public boolean isRun() {
        return run;
    }
    public void deleteants(){
        ants.clear();
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public Movement(int x, int y) {
        this.ants = new ArrayList<>();
        this.board = new Board(x, y);
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++)
                board.setValue(i, j, 0);
    }

    public void setMovementPattern(String movementPattern) {
        this.movementPattern = movementPattern;
    }

    /** @brief Metoda dodaje mrówkę do listy */
    public void addAnt(int x, int y) {
        Ant ant = new Ant(x, y);
        ants.add(ant);
    }

    public int getAntsSize() {
        return ants.size();
    }

    public String getMovementPattern() {
        return movementPattern;
    }

/** @brief Funkcja symulujaca przejscia mrowki
 *
 *   Funkcja na podstawie podanych danych wykonuje ruch do przodu,
 *   nadpisuje kolor pola, na ktorym sie znajduje oraz
 *   zmienia kierunek, w ktorym wykona nastepny krok
 *
 * @param i int ruch n-tej mrówki
 */
public Paint move(int i) {
        if (movementPattern.equals(""))
            setMovementPattern("LR");

        try {
            board.setValue(ants.get(i).getX(), ants.get(i).getY(), (board.getValue(ants.get(i).getX(), ants.get(i).getY()) + 1) % movementPattern.length());
            if (ants.get(i).getDirection() == 0) {

                ants.get(i).setY((ants.get(i).getY() + 1) % board.getY());
                if (movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'L' || movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'l')
                    ants.get(i).setDirection(3);
                else
                    ants.get(i).setDirection(1);
            } else if (ants.get(i).getDirection() == 1) {

                ants.get(i).setX((ants.get(i).getX() + 1) % board.getX());
                if (movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'L' || movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'l')
                    ants.get(i).setDirection(0);
                else
                    ants.get(i).setDirection(2);
            } else if (ants.get(i).getDirection() == 2) {

                ants.get(i).setY((ants.get(i).getY() - 1 + board.getY()) % board.getY());
                if (movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'L' || movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'l')
                    ants.get(i).setDirection(1);
                else
                    ants.get(i).setDirection(3);
            } else if (ants.get(i).getDirection() == 3) {

                ants.get(i).setX((ants.get(i).getX() - 1 + board.getX()) % board.getX());
                if (movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'L' || movementPattern.charAt(board.getValue(ants.get(i).getX(), ants.get(i).getY())) == 'l')
                    ants.get(i).setDirection(2);
                else
                    ants.get(i).setDirection(0);
            }
        }catch (IndexOutOfBoundsException e){

        };
        int x = ants.get(i).getX();
        int y = ants.get(i).getY();

        Color color = Color.WHITE;
        switch (board.getValue(x, y)) {
            case 0:
                color = Color.WHITE;
                break;

            case 1:
                color = Color.RED;
                break;

            case 2:
                color = Color.GREEN;
                break;
            case 3:
                color = Color.PURPLE;
                break;
            case 4:
                color = Color.BLUE;
                break;
            case 5:
                color = Color.LIGHTBLUE;
                break;
            case 6:
                color = Color.CHOCOLATE;
                break;
            case 7:
                color = Color.LIGHTGREEN;
                break;
            case 8:
                color = Color.BEIGE;
                break;
            case 9:
                color = Color.BLACK;
                break;
        }

        Paint p = new Paint(x, y, color);
        return p;


    }

}
