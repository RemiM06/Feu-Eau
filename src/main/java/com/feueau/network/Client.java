package com.feueau.network;

import com.corundumstudio.socketio.SocketIOClient;
import com.feueau.sae.AppSAE;
import com.feueau.sae.menus.composants.AttenteJoueurs;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;

import java.net.URISyntaxException;
import java.util.Map;

import static com.feueau.sae.AppSAE.primaryStage;

import static com.feueau.sae.AppSAE.primaryStage;

public class Client {

    private static Socket socket;
    public static void main(String[] args) throws URISyntaxException {


        socket = IO.socket("http://25.73.214.239:1234");
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

        socket.on("gameState", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Map<String, String> gameState = (Map<String, String>) args[0];
                System.out.println("Game state updated: " + gameState);
                // Mettre à jour l'affichage du jeu en fonction de gameState
            }
        });


        socket.connect();


    }

    public static void sendMove(String direction) {
        socket.emit("move", direction);
    }


}
