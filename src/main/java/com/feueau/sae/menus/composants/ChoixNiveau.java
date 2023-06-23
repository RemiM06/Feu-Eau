package com.feueau.sae.menus.composants;

import com.feueau.sae.graphiques.BackGroundImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ChoixNiveau {

    static BackGroundImage backGroundImage;

    public static void levelSelector(Scene sceneNiveaux, Stage stageMain) {



        Button niveau1 = new Button("Niveau 1");
        Button niveau2 = new Button("Niveau 2");
        Button niveau3 = new Button("Niveau 3");

        niveau1.setOnAction(event -> ouvrirNiveau(sceneNiveaux, "Niveau 1"));
        niveau2.setOnAction(event -> ouvrirNiveau(sceneNiveaux, "Niveau 2"));
        niveau3.setOnAction(event -> ouvrirNiveau(sceneNiveaux, "Niveau 3"));

        VBox vboxBoutons = new VBox(10);
        vboxBoutons.setPadding(new Insets(10));
        vboxBoutons.setAlignment(Pos.CENTER);
        vboxBoutons.getChildren().addAll(niveau1, niveau2, niveau3);

        ((BorderPane) sceneNiveaux.getRoot()).setCenter(vboxBoutons);

        backGroundImage = new BackGroundImage("/img/aokiji-vs-akainu.jpg");
        backGroundImage.appliquerBackground(sceneNiveaux);
        stageMain.setScene(sceneNiveaux);
    }

    private static Scene creerSceneNiveau(String niveau) {
        GridPane niveauGrid = new GridPane();

        Button bloc1 = new Button();
        Button bloc2 = new Button();
        Button bloc3 = new Button();

        niveauGrid.add(bloc1, 0, 0);
        niveauGrid.add(bloc2, 1, 0);
        niveauGrid.add(bloc3, 0, 1);

        Label labelNiveau = new Label("Niveau : " + niveau);
        niveauGrid.add(labelNiveau, 0, 0);

        Scene niveauScene = new Scene(niveauGrid, 700, 400);

        return niveauScene;
    }

    public static void ouvrirNiveau(Scene scene, String niveau) {
        Scene niveauScene = creerSceneNiveau(niveau);
        Stage stage = (Stage) scene.getWindow();
        stage.setScene(niveauScene);
    }

}
