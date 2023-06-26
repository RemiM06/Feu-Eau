package com.feueau.sae.menus.composants;


import com.feueau.datas.Partie;
import com.feueau.datas.RecupIDJoueur;
import com.feueau.datas.VerifConnexionPartie;
import com.feueau.network.Client;
import com.feueau.network.Serveur;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.net.URISyntaxException;

public class PopUpCreerPartie {

    private static String J1;

    private static String J2;

    public static void dialogCreationPartie(Stage primaryStage){

        Dialog<Void> dialogCreatePartie = new Dialog<>();
        dialogCreatePartie.initOwner(primaryStage);
        dialogCreatePartie.setTitle("Créer une partie");
        DialogPane dialogPane = dialogCreatePartie.getDialogPane();
        dialogPane.setPrefWidth(300);


        Label nomPartieLabel = new Label("Nom de la partie que vous souhaitez rejoindre: ");
        TextField nomPartieTextField = new TextField();

        Label mdpLabel = new Label("Mot de passe de la partie que vous souhaitez rejoindre: ");
        PasswordField mdpField = new PasswordField();

        VBox contentDialog = new VBox(10);
        contentDialog.getChildren().addAll(nomPartieLabel, nomPartieTextField, mdpLabel, mdpField);
        dialogPane.setContent(contentDialog);


        ButtonType validerButtonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        dialogCreatePartie.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

        Button validateButton = (Button) dialogPane.lookupButton(validerButtonType);
        validateButton.addEventFilter(ActionEvent.ACTION, event -> {
            String nomPartie = nomPartieTextField.getText();
            String mdpPartie = mdpField.getText();

            if(nomPartieTextField.getText().isBlank() || mdpField.getText().isBlank()){
                event.consume();
                Alertes.showAlert("Veillez à remplir tous les champs avant de les valider");
            }
            else{
                int IDJ1 = RecupIDJoueur.RecupIDAvecPseudo(J1 = PopUpConnection.getUsername());
                Partie.AjoutPartie(nomPartie, mdpPartie, IDJ1, 0,0);
                ChoixNiveau.levelSelector(primaryStage, nomPartie, mdpPartie);
            }

        });

        Serveur.main(new String[]{});

        dialogCreatePartie.showAndWait();

    }

    public static void dialogRejoindrePartie(Stage primaryStage) throws URISyntaxException {

        Dialog<Void> dialogRejoindrePartie = new Dialog<>();
        dialogRejoindrePartie.initOwner(primaryStage);
        dialogRejoindrePartie.setTitle("Rejoindre une partie");
        DialogPane dialogPane = dialogRejoindrePartie.getDialogPane();
        dialogPane.setPrefWidth(300);


        Label nomPartieLabel = new Label("Nom de la partie que vous souhaitez rejoindre: ");
        TextField nomPartieTextField = new TextField();

        Label mdpLabel = new Label("Mot de passe de la partie que vous souhaitez rejoindre: ");
        PasswordField mdpField = new PasswordField();

        VBox contentDialog = new VBox(10);
        contentDialog.getChildren().addAll(nomPartieLabel, nomPartieTextField, mdpLabel, mdpField);
        dialogPane.setContent(contentDialog);


        ButtonType validerButtonType = new ButtonType("Valider", ButtonBar.ButtonData.OK_DONE);
        dialogRejoindrePartie.getDialogPane().getButtonTypes().addAll(validerButtonType, ButtonType.CANCEL);

        Button validateButton = (Button) dialogPane.lookupButton(validerButtonType);
        validateButton.addEventFilter(ActionEvent.ACTION, event -> {
            String nomPartie = nomPartieTextField.getText();
            String mdpPartie = mdpField.getText();

            if(nomPartieTextField.getText().isBlank() || mdpField.getText().isBlank()){
                event.consume();
                Alertes.showAlert("Veillez à remplir tous les champs avant de les valider");
            }
            else{
                if(VerifConnexionPartie.Verif(nomPartie,mdpPartie)==1){
                    int IDJ2 = RecupIDJoueur.RecupIDAvecPseudo(J2 = PopUpConnection.getUsername());
                    int IDJ1 = 0;
                    Partie.AjoutPartie(nomPartie,mdpPartie,IDJ1,IDJ2,0);
                }
                else {
                    event.consume();
                    Alertes.showAlert("Nom de partie ou Mot de passe incorrect.");
                }
                int numNiveau = 1;
                AttenteJoueurs.sceneAttente(primaryStage, numNiveau);

            }

        });

        dialogRejoindrePartie.showAndWait();
        Client.main(new String[]{});

    }
}
