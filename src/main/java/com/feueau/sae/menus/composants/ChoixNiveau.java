package com.feueau.sae.menus.composants;

import com.feueau.sae.AppSAE;
import com.feueau.sae.graphiques.BackGroundImage;
import com.feueau.sae.level.Level;
import com.feueau.sae.partie.Partie;
import com.feueau.sae.partie.PartieClient;
import com.feueau.sae.partie.PartieServeur;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

import static com.feueau.sae.menus.composants.CreerBouton.creerBouton;

public class ChoixNiveau {

static BackGroundImage backGroundImage;
private static Scene sceneApp;



    public static void levelSelector(Stage primaryStage, String nomPartie, String mdpPartie) {

        BorderPane levelSelectorPane = new BorderPane();

        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");
        primaryStage.setTitle("Veuillez choisir une niveau");

        AtomicInteger numNiveau = new AtomicInteger();

        Button niveau1 = creerBouton("NIVEAU 1", Pos.CENTER, () -> {
            numNiveau.set(1);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau1.getStyleClass().add("one-piece-button");

        Button niveau2 = creerBouton("NIVEAU 2", Pos.CENTER, () -> {
            numNiveau.set(2);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau2.getStyleClass().add("one-piece-button");

        Button niveau3 = creerBouton("NIVEAU 3", Pos.CENTER, () -> {
            numNiveau.set(3);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau3.getStyleClass().add("one-piece-button");

        Button niveau4 = creerBouton("NIVEAU 4", Pos.CENTER, () -> {
            numNiveau.set(4);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau4.getStyleClass().add("one-piece-button");

        Button niveau5 = creerBouton("NIVEAU 5", Pos.CENTER, () -> {
            numNiveau.set(5);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau5.getStyleClass().add("one-piece-button");

        Button niveau6 = creerBouton("NIVEAU 6", Pos.CENTER, () -> {
            numNiveau.set(6);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau6.getStyleClass().add("one-piece-button");

        Button niveau7 = creerBouton("NIVEAU 7", Pos.CENTER, () -> {
            numNiveau.set(7);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau7.getStyleClass().add("one-piece-button");

        Button niveau8 = creerBouton("NIVEAU 8", Pos.CENTER, () -> {
            numNiveau.set(8);
            AttenteJoueurs.sceneAttente(primaryStage, numNiveau.get());
        });
        niveau8.getStyleClass().add("one-piece-button");

        Button retourBouton = creerBouton("RETOUR", Pos.BOTTOM_LEFT, () ->{
            CreerRejoindre.creerRejoindre(primaryStage);
        });
        retourBouton.getStyleClass().add("button");





        //VBox boutons niveaux
        VBox vboxBoutons = new VBox(10);
        vboxBoutons.setPadding(new Insets(10));
        vboxBoutons.setAlignment(Pos.CENTER);
        vboxBoutons.getChildren().addAll(niveau1, niveau2, niveau3, niveau4, niveau5, niveau6, niveau7, niveau8);
        levelSelectorPane.setCenter(vboxBoutons);

        VBox vboxRetour = new VBox(10);
        vboxRetour.setPadding(new Insets(10));
        vboxRetour.setAlignment(Pos.BOTTOM_LEFT);
        vboxRetour.getChildren().addAll(retourBouton);
        levelSelectorPane.setBottom(vboxRetour);

        Scene levelSelectorScene = new Scene(levelSelectorPane);
        backGroundImage.appliquerBackground(levelSelectorScene);



        levelSelectorScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE.equals(event.getCode())) {
                event.consume();
            }
        });


        primaryStage.setFullScreenExitHint("");
        primaryStage.setScene(levelSelectorScene);
        primaryStage.setFullScreen(true);

        levelSelectorScene.getStylesheets().add(ChoixNiveau.class.getResource("/styles.css").toExternalForm());

    }

    public static void levelSelectorLocal(Stage primaryStage){

        BorderPane levelSelectorPane = new BorderPane();

        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");
        primaryStage.setTitle("Veuillez choisir une niveau");


        Button niveau1 = creerBouton("NIVEAU 1", Pos.CENTER, () -> {
                Partie partie = new Partie(primaryStage,  new Level("Level 1"));
        });
        niveau1.getStyleClass().add("one-piece-button");

        AtomicInteger numNiveau = new AtomicInteger();

        Button niveau2 = creerBouton("NIVEAU 2", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 2"));

        });
        niveau2.getStyleClass().add("one-piece-button");

        Button niveau3 = creerBouton("NIVEAU 3", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 3"));
        });
        niveau3.getStyleClass().add("one-piece-button");

        Button niveau4 = creerBouton("NIVEAU 4", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 4"));
        });
        niveau4.getStyleClass().add("one-piece-button");

        Button niveau5 = creerBouton("NIVEAU 5", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 5"));
        });
        niveau5.getStyleClass().add("one-piece-button");

        Button niveau6 = creerBouton("NIVEAU 6", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 6"));
        });
        niveau6.getStyleClass().add("one-piece-button");

        Button niveau7 = creerBouton("NIVEAU 7", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 7"));
        });
        niveau7.getStyleClass().add("one-piece-button");

        Button niveau8 = creerBouton("NIVEAU 8", Pos.CENTER, () -> {
            Partie partie = new Partie(primaryStage,  new Level("Level 8"));
        });
        niveau8.getStyleClass().add("one-piece-button");

        Button quitterBouton = creerBouton("QUITTER", Pos.BOTTOM_LEFT, () -> {
            primaryStage.close();
        });
        quitterBouton.getStyleClass().add("button");





        //VBox boutons niveaux
        VBox vboxBoutons = new VBox(10);
        vboxBoutons.setPadding(new Insets(10));
        vboxBoutons.setAlignment(Pos.CENTER);
        vboxBoutons.getChildren().addAll(niveau1, niveau2, niveau3, niveau4, niveau5, niveau6, niveau7, niveau8);
        levelSelectorPane.setCenter(vboxBoutons);

        VBox vboxRetour = new VBox(10);
        vboxRetour.setPadding(new Insets(10));
        vboxRetour.setAlignment(Pos.BOTTOM_LEFT);
        vboxRetour.getChildren().addAll(quitterBouton);
        levelSelectorPane.setBottom(vboxRetour);

        Scene levelSelectorScene = new Scene(levelSelectorPane);
        backGroundImage.appliquerBackground(levelSelectorScene);





        levelSelectorScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE.equals(event.getCode())) {
                event.consume();
            }
        });


        primaryStage.setFullScreenExitHint("");
        primaryStage.setScene(levelSelectorScene);
        primaryStage.setFullScreen(true);

        levelSelectorScene.getStylesheets().add(ChoixNiveau.class.getResource("/styles.css").toExternalForm());

    }

    public static void setCurrentScene(Scene scene) {
        sceneApp = scene;
    }

}
