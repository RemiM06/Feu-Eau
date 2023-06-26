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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.UnknownHostException;

public class AppSAE extends Application {


    public static void lancement(String[] args) {
        AppSAE.launch(args);
    }

    private Scene niveauxScene;
    private Scene reglesScene;
    private BackGroundImage backGroundImage;
    private BackGroundImage titreImage;
    private Stage stageMain;

    private Button creerBouton(String texte, Pos position, Runnable action){
        return CreerBouton.creerBouton(texte, position, action);
    }

    @Override
    public void start(Stage stageMain) throws IOException {

        try {
            String[] command = {"java", "-Xmx1024m", "VotreClasse"};
            Process process = Runtime.getRuntime().exec(command);
            // ... Manipulation du processus si nécessaire
        } catch (IOException e) {
            e.printStackTrace();
        }

        BorderPane rootPane = new BorderPane();
        BorderPane reglesPane = new BorderPane();
        BorderPane niveauxPane = new BorderPane();
        this.stageMain = stageMain;



        //Titre
        Label titreAcceuil = new Label();
        titreAcceuil.getStyleClass().add("title-pixelart");



        niveauxScene = new Scene(niveauxPane, 700, 400);


        //Mise en place du background
        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");


        //Chargement de la police

        //Boutons
        Button jouerBouton = creerBouton("Jouer", Pos.CENTER, () -> {
            PopUpConnection.showLoginDialog(stageMain);
        });
        Button reglesBouton = creerBouton("Regles", Pos.CENTER, () ->
                {
                    Partie partie = new Partie(stageMain, new Level("Level 1"));
                }
        );



        reglesBouton.getStyleClass().add("one-piece-button");
        jouerBouton.getStyleClass().add("one-piece-button");

        Button closeBouton = creerBouton("QUITTER", Pos.BOTTOM_LEFT, () ->{
            stageMain.close();
        });

        //VBox boutonQuitter
        VBox quitterVBox = new VBox(10);
        quitterVBox.setAlignment(Pos.BOTTOM_LEFT);
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

        // Ajoutez l'ImageView à votre scène ou à un autre conteneur approprié


        Scene scene = new Scene(rootPane, 700, 400);
        backGroundImage.appliquerBackground(scene);

        //Import fichier de style
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());




        stageMain.setTitle("Feu & Eau! - 2 éléments: un seul objectif !");
        stageMain.setScene(scene);
        titreVBox.getChildren().add(imageView);
        stageMain.setFullScreen(true);
        stageMain.show();

    }


}