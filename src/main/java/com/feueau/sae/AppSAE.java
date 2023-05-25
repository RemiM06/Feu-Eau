package com.feueau.sae;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppSAE extends Application {


    public static void lancement(String[] args) {
        AppSAE.launch(args);
    }

    @Override
    public void start(Stage stageMain) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppSAE.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);

        stageMain.setFullScreen(true);
        stageMain.setTitle("Hello!");
        stageMain.setScene(scene);
        stageMain.show();
    }


}