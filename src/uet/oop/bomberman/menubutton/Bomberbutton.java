package uet.oop.bomberman.menubutton;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bomberbutton extends Button {
    private final String FONT = "res/font/kenvector_future.ttf";
    private final String BUTTON_PRESSED = "-fx-background-color: transparent; -fx-background-image: url('/res/backgr/red_button_pressed.png');";
    private final String BUTTON_FREE = "-fx-background-color: transparent; -fx-background-image: url('/res/backgr/red_button.png');";
    public Bomberbutton(String text) {
        setText(text);
        setButtonFont();
        setPrefHeight(190);
        setPrefWidth(49);
        setStyle(BUTTON_FREE);
        initButtonListener();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT), 23));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 23));
        }
    }

    private void setButtonPress() {
        setStyle(BUTTON_PRESSED);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initButtonListener() {

        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPress();
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setButtonReleasedStyle();
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }
}
