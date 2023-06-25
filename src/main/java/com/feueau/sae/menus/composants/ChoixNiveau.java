package com.feueau.sae.menus.composants;

import com.feueau.sae.graphiques.BackGroundImage;
import com.feueau.sae.level.Level;
import com.feueau.sae.partie.Partie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.feueau.sae.menus.composants.CreerBouton.creerBouton;

public class ChoixNiveau {

static BackGroundImage backGroundImage;

    public static void levelSelector(Stage primaryStage, String nomPartie, String mdpPartie) {

        BorderPane levelSelectorPane = new BorderPane();

        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");
        primaryStage.setTitle("Veuillez choisir une niveau");


        Button niveau1 = creerBouton("NIVEAU 1", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage, new Level("Level 1"));
        });
        niveau1.getStyleClass().add("one-piece-button");

        Button niveau2 = creerBouton("NIVEAU 2", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage, new Level("Level 2"));
        });
        niveau2.getStyleClass().add("one-piece-button");

        Button niveau3 = creerBouton("NIVEAU 3", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage, new Level("Level 3"));
        });
        niveau3.getStyleClass().add("one-piece-button");

        //VBox boutons niveaux
        VBox vboxBoutons = new VBox(10);
        vboxBoutons.setPadding(new Insets(10));
        vboxBoutons.setAlignment(Pos.CENTER);
        vboxBoutons.getChildren().addAll(niveau1, niveau2, niveau3);
        levelSelectorPane.setCenter(vboxBoutons);

        Scene levelSelectorScene = new Scene(levelSelectorPane);
        backGroundImage.appliquerBackground(levelSelectorScene);

        primaryStage.setScene(levelSelectorScene);
        primaryStage.setFullScreen(true);

        levelSelectorScene.getStylesheets().add(ChoixNiveau.class.getResource("/styles.css").toExternalForm());
    }
}
