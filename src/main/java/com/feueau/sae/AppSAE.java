package com.feueau.sae;

import com.feueau.sae.graphiques.BackGroundImage;
import com.feueau.sae.menus.composants.CreerBouton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;

import java.io.IOException;

public class AppSAE extends Application {


    public static void lancement(String[] args) {
        AppSAE.launch(args);
    }

    private Scene jeuScene;
    private Scene gameScene;
    private BackGroundImage backGroundImage;
    private Stage stageMain;

    private Button creerBouton(String texte, Pos position, Runnable action){
        return CreerBouton.creerBouton(texte, position, action);
    }

    @Override
    public void start(Stage stageMain) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppSAE.class.getResource("hello-view.fxml"));

        BorderPane rootPane = new BorderPane();
        this.stageMain = stageMain;

        //Titre
        Label titreAcceuil = new Label("Feu & Eau");
        titreAcceuil.setStyle("-fx-font-size: 24px;");

        //Mise en place du background
        backGroundImage = new BackGroundImage("/img/Akainu-vs-Aokiji.png");

        //Boutons
        Button jouerBouton = creerBouton("JOUER", Pos.CENTER, () -> System.out.println("à definir"));
        Button reglesBouton = creerBouton("REGLES", Pos.CENTER, () -> System.out.println("à definir"));

        //VBox boutons
        VBox boutonsVbox = new VBox(10);
        boutonsVbox.setAlignment(Pos.CENTER);
        boutonsVbox.getChildren().addAll(jouerBouton, reglesBouton, titreAcceuil);
        rootPane.setCenter(boutonsVbox);

        Scene scene = new Scene(rootPane, 700, 400);
        backGroundImage.appliquerBackground(scene);


        stageMain.setFullScreen(true);
        stageMain.setTitle("Feu & Eau! - 2 éléments: un seul objectif !");
        stageMain.setScene(scene);
        stageMain.show();
    }


}