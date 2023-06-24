package com.feueau.sae.menus.composants;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class PopUpCreerPartie {

    public static void dialogCreationPartie(Stage mainStage){
        Dialog<Pair<String, String>> dialogCreatePartie = new Dialog<>();
        dialogCreatePartie.initOwner(mainStage);
        dialogCreatePartie.setTitle("Créer une partie");
        DialogPane dialogPane = dialogCreatePartie.getDialogPane();
        dialogPane.setPrefWidth(300);


        Label nomPartieLabel = new Label("Nom de la partie que vous souhaitez rejoindre: ");
        TextField nomPartieTextField = new TextField();

        Label mdpLabel = new Label("Mot de passe de la partie que vous souhaitez rejoindre: ");
        PasswordField mdpField = new PasswordField();

        VBox contentDialog = new VBox(10);
        contentDialog.getChildren().addAll(nomPartieLabel, mdpLabel, nomPartieTextField, mdpField);
        dialogPane.setContent(contentDialog);


        ButtonType validerButtonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        dialogCreatePartie.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

        Button validateButton = (Button) dialogPane.lookupButton(validerButtonType);
        validateButton.addEventFilter(ActionEvent.ACTION, event -> {



        });

        // Affichez la dialog et attendez la réponse
        Optional<Pair<String, String>> result = dialogCreatePartie.showAndWait();
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
