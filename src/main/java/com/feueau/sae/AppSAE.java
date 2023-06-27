package com.feueau.sae;

import com.feueau.sae.graphiques.BackGroundImage;
import com.feueau.sae.menus.composants.ChoixNiveau;
import com.feueau.sae.level.Level;
import com.feueau.sae.menus.composants.CreerBouton;
import com.feueau.sae.menus.composants.PopUpConnection;
import com.feueau.sae.partie.Partie;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.UnknownHostException;

public class AppSAE extends Application {



    private static Scene currentScene;
    public static void lancement(Scene scene, String[] args) {
        currentScene = scene;
        AppSAE.launch(args);
    }

    private Scene niveauxScene;
    private Scene reglesScene;
    private BackGroundImage backGroundImage;
    private BackGroundImage titreImage;
    public static Stage primaryStage;
    private static Scene sceneAttente;

    private Button creerBouton(String texte, Pos position, Runnable action){
        return CreerBouton.creerBouton(texte, position, action);
    }

    public static void setSceneAttente(Scene scene){
        sceneAttente = scene;
        if(primaryStage != null){
            primaryStage.setScene(sceneAttente);

        }
    }

    public static Scene getScene(){
        return primaryStage.getScene();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        BorderPane rootPane = new BorderPane();
        BorderPane reglesPane = new BorderPane();
        BorderPane niveauxPane = new BorderPane();
        this.primaryStage = primaryStage;

        //Titre
        Label titreAcceuil = new Label();
        titreAcceuil.getStyleClass().add("title-pixelart");



        niveauxScene = new Scene(niveauxPane, 700, 400);


        //Mise en place du background
        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");

        //Boutons
        Button jouerBouton = creerBouton("JOUER EN LIGNE", Pos.CENTER, () -> {
            PopUpConnection.showLoginDialog(primaryStage);
        });

        Button reglesBouton = creerBouton("JEU EN LOCAL", Pos.CENTER, () -> {
                    ChoixNiveau.levelSelectorLocal(primaryStage);
                }
        );



        reglesBouton.getStyleClass().add("one-piece-button");
        jouerBouton.getStyleClass().add("one-piece-button");

        Button closeBouton = creerBouton("QUITTER", Pos.BOTTOM_LEFT, () -> {
            primaryStage.close();
        });



        //VBox boutonQuitter
        VBox quitterVBox = new VBox();
        quitterVBox.setAlignment(Pos.BOTTOM_LEFT);
        quitterVBox.setFillWidth(true);
        quitterVBox.getChildren().addAll(closeBouton);

        rootPane.setBottom(quitterVBox);


        //VBox boutons
        VBox boutonsVbox = new VBox(10);
        boutonsVbox.setAlignment(Pos.CENTER);
        boutonsVbox.getChildren().addAll(jouerBouton, reglesBouton);
        rootPane.setCenter(boutonsVbox);

        //VBox titre
        VBox titreVBox = new VBox(10);
        titreVBox.setAlignment(Pos.TOP_CENTER);
        titreVBox.getChildren().addAll(titreAcceuil);
        rootPane.setTop(titreVBox);



        //Image titre
        Image image = new Image(getClass().getResourceAsStream("/img/Titre.png"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(1110); // Définissez la largeur souhaitée de l'image
        imageView.setFitHeight(153); // Définissez la hauteur souhaitée de l'image




        Scene scene = new Scene(rootPane, 700, 400);
        backGroundImage.appliquerBackground(scene);

        //Import fichier de style
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());


        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                event.consume(); // Empêche l'événement de propagation supplémentaire
            }
        });


        this.primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Feu & Eau! - 2 éléments: un seul objectif !");
        this.primaryStage.setResizable(false);
        this.primaryStage.setFullScreenExitKeyCombination(null);
        primaryStage.setFullScreenExitHint("");
        this.primaryStage.setScene(scene);
        titreVBox.getChildren().add(imageView);
        this.primaryStage.setFullScreen(true);

        this.primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        this.primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                event.consume();
            }
        });
        this.primaryStage.show();

    }




}