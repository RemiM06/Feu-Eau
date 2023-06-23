package com.feueau.sae.menus.composants;

import com.feueau.sae.graphiques.BackGroundImage;
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
        BorderPane creerrejoindrePane = new BorderPane();

        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");
        primaryStage.setTitle("Veuillez choisir une option");

        Button creerBouton = creerBouton("CREER UNE PARTIE", Pos.CENTER, () ->{
                PopUpCreerPartie.dialogCreationPartie();
        });

        Button closeBouton = creerBouton("QUITTER", Pos.BOTTOM_LEFT, () ->{
            primaryStage.close();
        });

        //VBox boutonQuitter
        VBox quitterVBox = new VBox(10);
        quitterVBox.setAlignment(Pos.BOTTOM_LEFT);
        quitterVBox.getChildren().addAll(closeBouton);
        creerrejoindrePane.setBottom(quitterVBox);

        //VBox boutons
        VBox creerRejoindreVBox = new VBox(10);
        creerRejoindreVBox.setAlignment(Pos.CENTER);
        creerRejoindreVBox.getChildren().addAll(creerBouton);
        creerrejoindrePane.setCenter(creerRejoindreVBox);

        Scene creerrejoindreScene = new Scene(creerrejoindrePane);
        backGroundImage.appliquerBackground(creerrejoindreScene);


        // Configuration de la nouvelle scène sur la stage existante
        primaryStage.setScene(creerrejoindreScene);
        primaryStage.setFullScreen(true);

    }

    }

