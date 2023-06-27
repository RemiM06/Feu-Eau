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
import java.util.function.Consumer;

import static com.feueau.sae.AppSAE.primaryStage;

import static com.feueau.sae.AppSAE.primaryStage;

public class Client {

    public static Socket socket;

    private static Consumer<String> messageListener;

    public static void setMessageListener(Consumer<String> listener) {
        messageListener = listener;
    }
    public static void main(String[] args) throws URISyntaxException {
        String IpAjoin = RecupIPavecPartie.RecupIP(args[0]);
        socket = IO.socket("http://"+IpAjoin+":1234");
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
                System.out.println(message);
            }
        });

        socket.on("mess", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String message = (String) args[0];
                if (messageListener != null) {
                    messageListener.accept(message);
                }
            }
        });
        socket.connect();


    }



}
