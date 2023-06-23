package com.feueau.sae.menus.composants;

import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.util.Optional;

public class PopUpCreerPartie {

    public static void dialogCreationPartie(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Créer une partie");

        // Créez les contrôles pour les champs de nom de partie et de mot de passe
        TextField nomPartieTextField = new TextField();
        PasswordField motDePasseField = new PasswordField();

        // Créez le contenu de la dialog
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.addRow(0, new Label("Nom de la partie:"), nomPartieTextField);
        gridPane.addRow(1, new Label("Mot de passe:"), motDePasseField);
        dialog.getDialogPane().setContent(gridPane);

        // Ajoutez les boutons de validation et d'annulation
        ButtonType validerButtonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

        // Configurez l'action de validation
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == validerButtonType) {
                String nomPartie = nomPartieTextField.getText();
                String motDePasse = motDePasseField.getText();
                return new Pair<>(nomPartie, motDePasse);
            }
            return null;
        });

        // Affichez la dialog et attendez la réponse
        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(pair -> {
            String nomPartie = pair.getKey();
            String motDePasse = pair.getValue();
            // Traitez les données de la partie créée (envoyez-les au serveur, etc.)
            // ...
            // Ouvrez la nouvelle scène d'attente
            AttenteJoueurs.sceneAttente();
        });
    }
}
