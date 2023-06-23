package com.feueau.sae.menus.composants;

import javafx.scene.control.Alert;

public class Alertes {

    public static void showAlert(String messageErreur){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Attention, veuillez respecter les conditions de remplissages");
        alert.setHeaderText(null);
        alert.setContentText(messageErreur);
        alert.showAndWait();

    }
}
