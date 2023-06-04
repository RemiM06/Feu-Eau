package com.feueau.sae.graphiques;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.Objects;

public class BackGroundImage {
    private final Image backgroundImage;
    private final Background background;

    public BackGroundImage(String imagePath) {
        backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)));
        background = creerBackground();
    }

    private Background creerBackground() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundPosition backgroundPosition = BackgroundPosition.CENTER;
        BackgroundImage backgroundImage = new BackgroundImage(this.backgroundImage, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, backgroundPosition, backgroundSize);
        return new Background(backgroundImage);
    }

    public void appliquerBackground(Scene scene) {
        Pane root = (Pane) scene.getRoot();
        root.setBackground(background);
    }

    public void applyBackground(Stage stage) {
        Scene scene = stage.getScene();
        if (scene != null) {
            appliquerBackground(scene);
        }
    }
}
