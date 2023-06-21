package com.feueau.sae.menus.composants;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class PopUpInscription {

    public static Runnable showIncriptionDialog() {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Inscription");
        dialog.setHeaderText("Veuillez vous inscrire");

        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.add(new Label("Nom d'utilisateur:"), 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(new Label("Mot de passe:"), 0, 1);
        grid.add(passwordField, 1, 1);

        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
        dialog.getDialogPane().setContent(grid);

        // Configurez les actions pour les boutons
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // Effectuez ici le traitement de l'inscription
                String username = usernameField.getText();
                String password = passwordField.getText();
                System.out.println("Inscription en cours pour l'utilisateur : " + username);
            }
            return null;
        });

        dialog.showAndWait();
        return null;
    }
}
