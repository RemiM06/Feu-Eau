package com.feueau.sae.menus.composants;

import com.feueau.sae.level.Level;
import com.feueau.sae.partie.Partie;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class AttenteJoueurs {

    private static boolean joueur1Connecte = false;
    private static boolean joueur2Connecte = false;



    public static void sceneAttente(Stage primaryStage, int numNiveau) {

        BorderPane pane = new BorderPane();


        Color marron = Color.rgb(101, 67, 33);
        BackgroundFill backgroundFill = new BackgroundFill(marron, null, null);
        Background backgroundAttente = new Background(backgroundFill);
        pane.setBackground(backgroundAttente);

        Label titreAttente = new Label("ATTENTE DES JOUEURS");

        //VBox Gauche
        VBox joueur1VBox = new VBox();
        joueur1VBox.setAlignment(Pos.CENTER);

        //Image Gauche
        Image imageJ1 = new Image(AttenteJoueurs.class.getResourceAsStream("/img/Akainu-droite.png"));
        ImageView imageView = new ImageView(imageJ1);
        imageView.setFitWidth(250);
        imageView.setFitHeight(500);


        //VBox Droite
        VBox joueur2VBox = new VBox();
        joueur2VBox.setAlignment(Pos.CENTER);

        //Image Droite
        Image imageJ2 = new Image(AttenteJoueurs.class.getResourceAsStream("/img/Aokiji-gauche-eau.png"));
        ImageView imageViewJ2 = new ImageView(imageJ2);


        if (joueur1Connecte) {

            pane.setLeft(joueur1VBox);
            joueur1VBox.getChildren().add(imageView);
        }

        if (joueur2Connecte) {
            pane.setRight(joueur2VBox);
            joueur2VBox.getChildren().add(imageViewJ2);
        }


        if(joueur1Connecte && joueur2Connecte) {
            if(numNiveau == 1) {
                Group root = new Group();
                Scene sceneJeu = new Scene(root, 700, 400);
                Partie partie = new Partie(sceneJeu, root, new Level("Level 1"));
                primaryStage.setScene(partie.getScene());
                primaryStage.setFullScreen(true);
            }
            else if(numNiveau == 2) {
                Group root = new Group();
                Scene sceneJeu = new Scene(root, 700, 400);
                Partie partie = new Partie(sceneJeu, root, new Level("Level 2"));
                primaryStage.setScene(partie.getScene());
                primaryStage.setFullScreen(true);
            }
            else if(numNiveau == 3) {
                Group root = new Group();
                Scene sceneJeu = new Scene(root, 700, 400);
                Partie partie = new Partie(sceneJeu, root, new Level("Level 3"));
                primaryStage.setScene(partie.getScene());
                primaryStage.setFullScreen(true);
            }

        }
        else {

        }

        Scene sceneAttente = new Scene(pane);

        sceneAttente.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE.equals(event.getCode())) {
                event.consume();
            }
        });


        primaryStage.setScene(sceneAttente);
        primaryStage.setFullScreen(true);


    }




}
