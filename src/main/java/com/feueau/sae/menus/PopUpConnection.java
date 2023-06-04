package com.feueau.sae.menus;

import javafx.scene.control.*;
import javafx.scene.layout.VBox;

public class PopUpConnection {

    public static void showLoginDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Connexion/Inscription");

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setPrefWidth(300);

        Label usernameLabel = new Label("Nom d'utilisateur:");
        TextField usernameTextField = new TextField();

        VBox content = new VBox(10);
        content.getChildren().addAll(usernameLabel, usernameTextField);

        dialogPane.setContent(content);

        ButtonType loginButtonType = new ButtonType("Se connecter");
        ButtonType registerButtonType = new ButtonType("S'inscrire");

        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, registerButtonType, ButtonType.CANCEL);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                // Action à exécuter lors du clic sur le bouton "Se connecter"
                String username = usernameTextField.getText();
                System.out.println("Connexion - Nom d'utilisateur : " + username);
            } else if (dialogButton == registerButtonType) {
                // Action à exécuter lors du clic sur le bouton "S'inscrire"
                String username = usernameTextField.getText();
                System.out.println("Inscription - Nom d'utilisateur : " + username);
            }
            return null;
        });

        dialog.showAndWait();
    }
}
