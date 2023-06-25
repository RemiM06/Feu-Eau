package com.feueau.network;

import com.feueau.sae.menus.composants.AttenteJoueurs;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws URISyntaxException {

        Socket socket = IO.socket("http://25.73.214.239:1234");
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

        socket.on("joueurConnecte", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String joueur1ConnecteMsg = (String) args[0];
                String joueur2ConnecteMsg = (String) args[1];

                boolean joueur1Connecte = Boolean.parseBoolean(joueur1ConnecteMsg);
                boolean joueur2Connecte = Boolean.parseBoolean(joueur2ConnecteMsg);

                AttenteJoueurs.setJoueur1Connecte(joueur1Connecte);
                AttenteJoueurs.setJoueur2Connecte(joueur2Connecte);

                System.out.println("Joueur 1 connecté : " + joueur1Connecte);
                System.out.println("Joueur 2 connecté : " + joueur2Connecte);
            }
        });

        socket.connect();

    }
}
