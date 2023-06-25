package com.feueau.sae.menus.composants;

import com.feueau.sae.AppSAE;
import com.feueau.sae.graphiques.BackGroundImage;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static com.feueau.sae.menus.composants.CreerBouton.creerBouton;

public class CreerRejoindre {

    static BackGroundImage backGroundImage;

    public static void creerRejoindre(Stage primaryStage){

        BorderPane creerRejoindrePane = new BorderPane();

        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");
        primaryStage.setTitle("Veuillez choisir une option");

        Button creerBouton = creerBouton("CREER UNE PARTIE", Pos.CENTER, () ->{
            PopUpCreerPartie.dialogCreationPartie(primaryStage);
        });
        creerBouton.getStyleClass().add("one-piece-button-partie");

        Button rejoindreBouton = creerBouton("REJOINDRE UNE PARTIE", Pos.CENTER, () ->{
            PopUpCreerPartie.dialogRejoindrePartie(primaryStage);
        });
        rejoindreBouton.getStyleClass().add("one-piece-button-partie");

        Button closeBouton = creerBouton("QUITTER", Pos.BOTTOM_LEFT, () -> {
            primaryStage.close();
        });
        closeBouton.getStyleClass().add("one-piece-button");


        //VBox boutonQuitter
        VBox quitterVBox = new VBox(10);
        quitterVBox.setAlignment(Pos.BOTTOM_LEFT);
        quitterVBox.getChildren().addAll(closeBouton);
        creerRejoindrePane.setBottom(quitterVBox);


        //VBox boutons
        VBox creerRejoindreVBox = new VBox(10);
        creerRejoindreVBox.setAlignment(Pos.CENTER);
        creerRejoindreVBox.getChildren().addAll(creerBouton, rejoindreBouton);
        creerRejoindrePane.setCenter(creerRejoindreVBox);

        Scene creerRejoindreScene = new Scene(creerRejoindrePane);
        backGroundImage.appliquerBackground(creerRejoindreScene);


        // Configuration de la nouvelle scène sur la stage existante
        primaryStage.setScene(creerRejoindreScene);
        primaryStage.setFullScreen(true);

        creerRejoindreScene.getStylesheets().add(ChoixNiveau.class.getResource("/styles.css").toExternalForm());

    }

    }

