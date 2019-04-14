/**
 * @file Main.java
 * @brief kontroler pliku sample.fxml
 *
 * @author Damian Charkiewicz and Jakub Glinka
 * @date 06.06.2018r
 *
 */

package sample.controllers;

import javafx.animation.AnimationTimer;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.Movement;
import sample.Paint;

import java.util.Random;

public class Controller {

    Movement movement = new Movement(600, 500);
    /**
     *implementacja formatek fxml
     */
    @FXML
    public Canvas antCanvas;

    @FXML
    public TextField behaviorTextField;

    @FXML
    public Button startButton;

    @FXML
    public Button randomButton;

    @FXML
    public ListView<Object> antListView;

    @FXML
    public Button resetButton;

    @FXML
    public Slider speedSlider;

    private IntegerProperty cycle;

    private DoubleProperty speedProperty;

    private ObservableList<Object> anttableObjects;

    private AnimationTimer timer;

    private String behavior = "";

    private GraphicsContext gc;

    private double x = 0;

    private double y = 0;

    /** @ brief Metoda uruchamiająca się wywołuje się wraz z otwarciem okna i obsługoje propertki i animacje mrówki */
    public void initialize() {
        gc = antCanvas.getGraphicsContext2D();

        speedSlider.setMin(1);
        speedSlider.setMax(100);
        speedSlider.setValue(10);

        ListProperty<Object> objects = new SimpleListProperty<>();
        anttableObjects = FXCollections.observableArrayList();
        objects.set(anttableObjects);
        antListView.itemsProperty().bindBidirectional(objects);

        cycle = new SimpleIntegerProperty(0);
        speedProperty = new SimpleDoubleProperty(1);
        speedSlider.valueProperty().bindBidirectional(speedProperty);


        System.out.println(speedSlider.getValue());

        timer = new AnimationTimer() {

            private long lastUpdate = 0;


            @Override
            public void handle(long now) {
                if (now - lastUpdate >= (100_000_000 / speedProperty.getValue())) {
                    //movement.move();
                    if (movement.isUpdate() == true) {
                        behavior = behaviorTextField.getText();

                        movement.setMovementPattern(behavior);
                        movement.setUpdate(false);
                    }
                    if (movement.isRun() == true) {
                        Paint p;
                        for (int i = 0; i < movement.getAntsSize(); i++) {
                            p = movement.move(i);
                            drawPixel(p);
                        }
                    }

                    lastUpdate = now;


                }

            }
        };

    }
    /** @brief Metoda rysująca pierwszy punkt mrówki i pobiera współrzedne mrówki, dodanie mrówki do listy */
    @FXML
    public void drawCanvas() {

        gc.setStroke(Color.RED);

        try {
            antCanvas.setOnMousePressed(event -> {
                gc.beginPath();
                //rysowanie
                x = event.getSceneX() - 269;
                y = event.getSceneY() - 59;
                movement.addAnt((int) x, (int) y);
                gc.setFill(Color.RED);
                gc.fillRect(x, y, 1, 1);
                gc.stroke();
                //dodanie wspolrzednych do listy
                anttableObjects.add(x + "x" + y);
            });

        } catch (Exception e) {
            System.out.println(e);
            System.exit(0);
        }

    }


    /** @brief Metoda rozpoczynająca ruch mrówki lub zmienia zachowanie  */
    public void startButton(ActionEvent actionEvent) {
        behavior = behaviorTextField.getText();
        timer.start();
        if (behavior == "") movement.setMovementPattern("LR");
        if (movement.getMovementPattern() == "")
            movement.setMovementPattern(behavior);
        movement.setRun(true);
        movement.setUpdate(true);
    }


    /** @brief Metoda wyczyszczająca tablicę */
    public void resetButton(ActionEvent actionEvent) {
        timer.stop();
        gc.clearRect(0, 0, 633, 484);
        anttableObjects.clear();
        movement.setRun(false);
        movement.deleteants();
        movement.clear();
    }


    /** @brief Metoda losowania i wypisania sekwencji ruchów */
    public void randomButton(ActionEvent actionEvent) {
        Random rand = new Random();

        int n = rand.nextInt(10) + 2;

        for (int i = 0; i < n; i++) {
            int be = rand.nextInt(2);

            if (be == 0)
                behavior += "R";
            else if (be == 1)
                behavior += "L";
        }
        behavior = behavior.replace(behaviorTextField.getText(), "");
        behaviorTextField.setText(behavior);
        behavior = "";
    }


    /** @brief Metoda wstrzymująca ruch mrówki */
    public void pauseButton(ActionEvent actionEvent) {
        timer.stop();
        movement.setRun(false);
    }

    /** @brief Metoda malująca pola mrówki */
    public void drawPixel(Paint paint) {
        gc.setFill(paint.getColor());
        gc.fillRect(paint.getX(), paint.getY(), 1, 1);
        gc.stroke();
    }

}