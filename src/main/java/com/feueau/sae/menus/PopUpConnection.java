package com.feueau.sae.menus;

import com.feueau.network.recuperation.IPUtilisateur;
import com.feueau.sae.menus.composants.CreerBouton;
import com.feueau.sae.menus.composants.PopUpInscription;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PopUpConnection {

    private static Button creerBouton(String texte, Pos position, Runnable action){
        return CreerBouton.creerBouton(texte, position, action);
    }

    public static void showLoginDialog() throws UnknownHostException {
        Dialog<ButtonType> dialogConnexion = new Dialog<>();
        dialogConnexion.setTitle("Connexion");

        DialogPane dialogPane = dialogConnexion.getDialogPane();
        dialogPane.setPrefWidth(300);

        String ipAdress = IPUtilisateur.getIPAddress();

        Label usernameLabel = new Label("Nom d'utilisateur:");
        TextField usernameTextField = new TextField();

        Label passwordLabel = new Label("Mot de passe:");
        TextField passwordTextField = new TextField();

        Label ipLabel = new Label("Votre adresse IP:" +
                ipAdress);




        VBox content = new VBox(10);
        content.getChildren().addAll(usernameLabel, usernameTextField, ipLabel, passwordLabel, passwordTextField);


        dialogPane.setContent(content);

        ButtonType loginButtonType = new ButtonType("Se connecter");
        ButtonType registerButtonType = new ButtonType("S'inscrire");
/*
        registerButtonType.setOnAction(event -> {
            new PopUpInscription();

        });*/



        dialogConnexion.getDialogPane().getButtonTypes().addAll(loginButtonType, registerButtonType, ButtonType.CANCEL);

        //TODO Ajouter la requete SQL qui crée un ID au joueur quand il s'inscrit

        dialogConnexion.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {

                String username = usernameTextField.getText();
                System.out.println("Connexion - Nom d'utilisateur : " + username);
                System.out.println("Connexion - IPAdress : " + ipAdress);

            }
            else{

            }
            return null;
        });

        dialogConnexion.showAndWait();
    }
}
