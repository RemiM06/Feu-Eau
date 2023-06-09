package com.feueau.sae.menus.composants;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.feueau.datas.Joueur1ou2;
import com.feueau.datas.RecupIDJoueur;
import com.feueau.network.Serveur;
import com.feueau.sae.level.Level;
import com.feueau.sae.partie.Partie;
import com.feueau.sae.partie.PartieClient;
import com.feueau.sae.partie.PartieServeur;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.corundumstudio.socketio.SocketIOClient;


public class AttenteJoueurs {

    private static int levelNum = 1;

    private static int idJoueur;
    private static String nomPartie;

    public static int getLevelNum() {
        return levelNum;
    }

    public static void incrementLevelNum() {
        levelNum = (levelNum % 3) + 1;
    }

    private static boolean joueur1Connecte = true;
    private static boolean joueur2Connecte = false;

    public static void setJoueur1Connecte(boolean value) {
        joueur1Connecte = value;
    }

    public static void setJoueur2Connecte(boolean value) {
        joueur2Connecte = value;
    }
    public static boolean isJoueur1Connecte() {
        return joueur1Connecte;
    }

    public static boolean isJoueur2Connecte() {
        return joueur2Connecte;
    }


    public static void updateConnectedClients() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String joueur1ConnecteMsg = objectMapper.writeValueAsString(joueur1Connecte);
        String joueur2ConnecteMsg = objectMapper.writeValueAsString(joueur2Connecte);



        for (SocketIOClient client : Serveur.getConnectedClients()) {
            client.sendEvent("joueurConnecte", joueur1ConnecteMsg, joueur2ConnecteMsg);
        }

    }



    public static void sceneAttente(Stage primaryStage, int numNiveau)  {

        System.out.println(joueur1Connecte);
        System.out.println(joueur2Connecte);
        BorderPane pane = new BorderPane();

        Color marron = Color.rgb(101, 67, 33);
        BackgroundFill backgroundFill = new BackgroundFill(marron, null, null);
        Background backgroundAttente = new Background(backgroundFill);
        pane.setBackground(backgroundAttente);

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


        Platform.runLater(() ->{

            try {
                updateConnectedClients();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }

        if (joueur1Connecte) {
            pane.setLeft(joueur1VBox);
            joueur1VBox.getChildren().add(imageView);
        }

        if (joueur2Connecte) {
            pane.setRight(joueur2VBox);
            joueur2VBox.getChildren().add(imageViewJ2);
        }



        if(joueur1Connecte && joueur2Connecte) {

            idJoueur = RecupIDJoueur.RecupIDAvecPseudo(PopUpConnection.getUsername());
            nomPartie = PopUpCreerPartie.getNomPartie();
            String NumJoueur = Joueur1ou2.verifierIdJoueur(nomPartie,idJoueur);

            if(numNiveau == 1) {
                if(NumJoueur.equals("Joueur 1")){
                    PartieServeur PartieServeur = new PartieServeur(primaryStage, new Level("Level 1"));
                } else if (NumJoueur.equals("Joueur 2")) {
                    PartieClient PartieClient = new PartieClient(primaryStage, new Level("Level 1"));
                }
            }
            else if(numNiveau == 2) {
                if(NumJoueur.equals("Joueur 1")){
                    PartieServeur PartieServeur = new PartieServeur(primaryStage, new Level("Level 2"));
                } else if (NumJoueur.equals("Joueur 2")) {
                    PartieClient PartieClient = new PartieClient(primaryStage, new Level("Level 2"));
                }
            }
            else if(numNiveau == 3) {
                if(NumJoueur.equals("Joueur 1")){
                    PartieServeur PartieServeur = new PartieServeur(primaryStage, new Level("Level 3"));
                } else if (NumJoueur.equals("Joueur 2")) {
                    PartieClient PartieClient = new PartieClient(primaryStage, new Level("Level 3"));
                }
            }

        }});



        Scene sceneAttente = new Scene(pane);

        sceneAttente.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (KeyCode.ESCAPE.equals(event.getCode())) {
                event.consume();
            }
        });

        primaryStage.setFullScreenExitHint("");
        primaryStage.setScene(sceneAttente);
        primaryStage.setFullScreen(true);


    }




}
