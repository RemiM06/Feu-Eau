package com.feueau.sae.menus.composants;

import com.feueau.datas.Utilisateur;
import com.feueau.network.recuperation.IPUtilisateur;
import com.feueau.sae.AppSAE;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.UnknownHostException;

public class PopUpConnection {

    public static void showLoginDialog(Stage primaryStage) {
        Dialog<Void> dialogConnexion = new Dialog<>();
        dialogConnexion.initOwner(primaryStage);
        dialogConnexion.setTitle("Connexion/Inscription");

        DialogPane dialogPane = dialogConnexion.getDialogPane();
        dialogPane.setPrefWidth(300);

        Label usernameLabel = new Label("Nom d'utilisateur:");
        TextField usernameTextField = new TextField();

        Label passwordLabel = new Label("Mot de passe:");
        TextField passwordTextField = new TextField();

        String adresseIP = IPUtilisateur.getIPAddress();

        Label ipLabel = new Label("Votre adresse IP: " + adresseIP);

        VBox content = new VBox(10);
        content.getChildren().addAll(usernameLabel, usernameTextField, ipLabel, passwordLabel, passwordTextField);

        dialogPane.setContent(content);

        ButtonType loginButtonType = new ButtonType("Se connecter");
        ButtonType registerButtonType = new ButtonType("S'inscrire");

        dialogConnexion.getDialogPane().getButtonTypes().addAll(loginButtonType, registerButtonType, ButtonType.CANCEL);

        Button loginButton = (Button) dialogPane.lookupButton(loginButtonType);
        loginButton.addEventFilter(ActionEvent.ACTION, event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();

            if(usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank()){
                event.consume();
                Alertes.showAlert("Remplissez tous les champs avant de les valider");

            }
            else{

                System.out.println("Inscription - Nom d'utilisateur : " + username);
                System.out.println("Inscription - IPAdress : " + adresseIP);
                System.out.println("Inscription - Mot de Passe : " + password);
                Utilisateur.AjoutUtilisateur(username, password, adresseIP);
                CreerRejoindre.creerRejoindre(primaryStage);
            }




        });

        Button registerButton = (Button) dialogPane.lookupButton(registerButtonType);
        registerButton.addEventFilter(ActionEvent.ACTION, event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();



             if(usernameTextField.getText().isBlank() || passwordTextField.getText().isBlank()){
                 event.consume();
                 Alertes.showAlert("Remplissez tous les champs avant de les valider");

             }
             else{
                 System.out.println("Inscription - Nom d'utilisateur : " + username);
                 System.out.println("Inscription - IPAdress : " + adresseIP);
                 System.out.println("Inscription - Mot de Passe : " + password);
                 Utilisateur.AjoutUtilisateur(username, password, adresseIP);

                 CreerRejoindre.creerRejoindre(primaryStage);


             }



        });

        dialogConnexion.showAndWait();
    }
}




