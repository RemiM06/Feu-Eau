package com.feueau.sae.menus.composants;

import com.feueau.datas.Utilisateur;
import com.feueau.network.recuperation.IPUtilisateur;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.UnknownHostException;

public class PopUpConnection {

    public static void showLoginDialog() throws UnknownHostException {
        Dialog<Void> dialogConnexion = new Dialog<>();
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

            System.out.println("Connexion - Nom d'utilisateur : " + username);
            System.out.println("Connexion - IPAdress : " + adresseIP);
            System.out.println("Connexion - Mot de Passe : " + password);



        });

        Button registerButton = (Button) dialogPane.lookupButton(registerButtonType);
        registerButton.addEventFilter(ActionEvent.ACTION, event -> {
            String username = usernameTextField.getText();
            String password = passwordTextField.getText();

            System.out.println("Inscription - Nom d'utilisateur : " + username);
            System.out.println("Inscription - IPAdress : " + adresseIP);
            System.out.println("Inscription - Mot de Passe : " + password);

            Utilisateur.AjoutUtilisateur(username, password, adresseIP);

        });

        dialogConnexion.showAndWait();
    }
}




