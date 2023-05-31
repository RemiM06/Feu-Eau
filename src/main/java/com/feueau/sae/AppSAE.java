package com.feueau.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.net.URL;

import java.io.IOException;

public class AppSAE extends Application {


    public static void lancement(String[] args) {
        AppSAE.launch(args);
    }

    @Override
    public void start(Stage stageMain) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppSAE.class.getResource("hello-view.fxml"));
        Pane rootPane = new Pane();

        URL urlImage = getClass().getResource("/img/Akainu-vs-Aokiji.png");

        Image backgroundImage = new Image(urlImage.toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                                                            BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        rootPane.setBackground(new Background(background));



        Scene scene = new Scene(rootPane, 700, 400);
        


        stageMain.setFullScreen(true);
        stageMain.setTitle("Hello!");
        stageMain.setScene(scene);
        stageMain.show();
    }


}