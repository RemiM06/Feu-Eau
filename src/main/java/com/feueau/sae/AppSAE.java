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
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.UnknownHostException;

public class AppSAE extends Application {


    public static void lancement(String[] args) {
        AppSAE.launch(args);
    }

    private Scene niveauxScene;
    private Scene reglesScene;
    private BackGroundImage backGroundImage;
    private Stage stageMain;

    private Button creerBouton(String texte, Pos position, Runnable action){
        return CreerBouton.creerBouton(texte, position, action);
    }

    @Override
    public void start(Stage stageMain) throws IOException {

        BorderPane rootPane = new BorderPane();
        BorderPane reglesPane = new BorderPane();
        BorderPane niveauxPane = new BorderPane();
        this.stageMain = stageMain;

        //Titre
        Label titreAcceuil = new Label("Feu & Eau");
        titreAcceuil.setStyle("-fx-font-size: 72px;");

        //Regles
        reglesScene = new Scene(reglesPane, 700, 400);

        niveauxScene = new Scene(niveauxPane, 700, 400);


        //Mise en place du background
        backGroundImage = new BackGroundImage("/img/Akainu-vs-Aokiji.png");

        //Boutons
        Button jouerBouton = creerBouton("JOUER", Pos.CENTER, () -> {
            PopUpConnection.showLoginDialog();
        });
        Button reglesBouton = creerBouton("REGLES", Pos.CENTER, () ->
                {
                    Group root = new Group();
                    Scene sceneJeu = new Scene(root,800,450);
                    Partie partie = new Partie(sceneJeu, root, new Level("Level 1"));
                    stageMain.setScene(partie.getScene());
                    stageMain.setFullScreen(true);
                }
        );

        Button choixNiveau = creerBouton("TEST", Pos.CENTER, () ->{
            ChoixNiveau.levelSelector(niveauxScene, stageMain);
        });

        //VBox boutons
        VBox boutonsVbox = new VBox(10);
        boutonsVbox.setAlignment(Pos.CENTER);
        boutonsVbox.getChildren().addAll(jouerBouton, reglesBouton, choixNiveau);
        rootPane.setCenter(boutonsVbox);

        //VBox titre
        VBox titreVBox = new VBox(10);
        titreVBox.setAlignment(Pos.TOP_CENTER);
        titreVBox.getChildren().addAll(titreAcceuil);
        rootPane.setTop(titreVBox);

        Scene scene = new Scene(rootPane, 700, 400);
        backGroundImage.appliquerBackground(scene);


        stageMain.setFullScreen(false);
        stageMain.setTitle("Feu & Eau! - 2 éléments: un seul objectif !");
        stageMain.setScene(scene);
        stageMain.show();
    }


}