package com.feueau.sae;

import com.feueau.sae.graphiques.BackGroundImage;
import com.feueau.sae.menus.PopUpConnection;
import com.feueau.sae.menus.composants.CreerBouton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.net.URL;

import java.io.IOException;

public class AppSAE extends Application {


    public static void lancement(String[] args) {
        AppSAE.launch(args);
    }

    private Scene jeuScene;
    private Scene gameScene;
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
        this.stageMain = stageMain;

        //Titre
        Label titreAcceuil = new Label("Feu & Eau");
        titreAcceuil.setStyle("-fx-font-size: 72px;");

        //Regles
        reglesScene = new Scene(reglesPane, 700, 400);

        //Mise en place du background
        backGroundImage = new BackGroundImage("/img/Akainu-vs-Aokiji.png");

        //Boutons
        Button jouerBouton = creerBouton("JOUER", Pos.CENTER, () -> PopUpConnection.showLoginDialog());
        Button reglesBouton = creerBouton("REGLES", Pos.CENTER, () -> stageMain.setScene(reglesScene));

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

        Scene scene = new Scene(rootPane, 700, 400);
        backGroundImage.appliquerBackground(scene);


        stageMain.setFullScreen(true);
        stageMain.setTitle("Feu & Eau! - 2 éléments: un seul objectif !");
        stageMain.setScene(scene);
        stageMain.show();
    }


}