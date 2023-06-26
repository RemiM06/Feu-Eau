package com.feueau.network;

import com.feueau.sae.joueur.Joueur;
import com.feueau.sae.menus.composants.AttenteJoueurs;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.net.URISyntaxException;

import static com.feueau.sae.AppSAE.primaryStage;

public class Client {

    private static Joueur joueur = new Joueur();

    public static void main(String[] args) throws URISyntaxException {
        Socket socket = IO.socket("http://25.73.214.239:1234");
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connected to server");
                AttenteJoueurs.setJoueur2Connecte(true);

                Platform.runLater(() -> {
                    try {
                        AttenteJoueurs.sceneAttente(primaryStage, AttenteJoueurs.getLevelNum());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                socket.emit("joueurConnecte", AttenteJoueurs.isJoueur1Connecte(), AttenteJoueurs.isJoueur2Connecte());
            }
        }).on("chat", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String message = (String) args[0];
                System.out.println("Message received from server: " + message);
            }
        });

        socket.connect();

        try {
            java.net.Socket clientSocket = new java.net.Socket("25.31.110.196", 1235);
            System.out.println("Connected to server");

            DataInputStream input = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream output = new DataOutputStream(clientSocket.getOutputStream());

            // Assume player2 is the player object on the client.
            Thread thread = new Thread(() -> {
                while (true) {
                    try {
                        // Send data to the server whenever a key is pressed.
                        output.writeUTF(joueur.getX() + "," + joueur.getY() + "," + joueur.getxVelocity() + "," + joueur.getyVelocity());
                        output.flush();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });

            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
