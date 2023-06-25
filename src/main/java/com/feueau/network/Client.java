package com.feueau.network;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws URISyntaxException {

        Socket socket = IO.socket("http://25.73.216.51:1234");
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connected to server");
                socket.emit("chat", "Hello from client");
            }
        }).on("chat", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String message = (String) args[0];
                System.out.println("Message received from server: " + message);
            }
        });

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connected to server");

                AttenteJoueurs.setJoueur1Connecte(true);

                // Envoyer l'état de connexion des joueurs au serveur
                socket.emit("joueurConnecte", AttenteJoueurs.isJoueur1Connecte(), AttenteJoueurs.isJoueur2Connecte());

            }
        });

        socket.connect();

    }
}
