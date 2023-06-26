package com.feueau.network;

import com.feueau.sae.menus.composants.AttenteJoueurs;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;

import java.net.URISyntaxException;

import static com.feueau.sae.AppSAE.primaryStage;

public class Client {
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
    }
}
