package com.feueau.sae.menus.composants;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class  CreerBouton {
    
    public static  Button creerBouton(String text, Pos position, Runnable action){
        Button bouton = new Button(text);
        bouton.setOnAction(actionEvent -> action.run());

        HBox.setHgrow(bouton, Priority.ALWAYS);
        HBox hboxBouton = new HBox(bouton);
        hboxBouton.setAlignment(position);
        return bouton;
    }
}
