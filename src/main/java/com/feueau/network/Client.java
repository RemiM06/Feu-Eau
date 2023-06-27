package com.feueau.network;

import com.corundumstudio.socketio.SocketIOClient;
import com.feueau.datas.RecupIPavecPartie;
import com.feueau.sae.AppSAE;
import com.feueau.sae.joueur.Joueur;
import com.feueau.sae.menus.composants.AttenteJoueurs;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import javafx.application.Platform;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.feueau.sae.AppSAE.primaryStage;

import static com.feueau.sae.AppSAE.primaryStage;

public class Client {

    public static Socket socket;

    public static String argsOutsideClass = null;
    public static void main(String[] args) throws URISyntaxException {
        String IpAjoin = RecupIPavecPartie.RecupIP(args[0]);
        socket = IO.socket("http://"+IpAjoin+":1234");
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println("Connected to server");
                AttenteJoueurs.setJoueur2Connecte(true);
                // on peuy pas juste faire un public ou je sais quoi sur le socket et pouvoir y acceder partout ? parce que en soit on rest co sur le meme socket du debu a la fin

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

        socket.on("mess", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                System.out.println(args[0]);
                argsOutsideClass = (String) args[0];
        }});

        socket.connect();


    }



}
