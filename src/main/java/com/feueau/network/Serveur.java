package com.feueau.network;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.feueau.sae.AppSAE;
import com.feueau.sae.menus.composants.AttenteJoueurs;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.feueau.sae.AppSAE.primaryStage;


public class Serveur {

    private static List<SocketIOClient> connectedClients = new ArrayList<>();

    private static Map<String, String> gameState = new ConcurrentHashMap<>();

    public static List<SocketIOClient> getConnectedClients(){
        return connectedClients;
    }
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("25.73.216.51");
        config.setPort(1234);




        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {

                System.out.println("A client has connected");
                connectedClients.add(client);
                AttenteJoueurs.setJoueur2Connecte(true);

                Platform.runLater(() ->{
                    AttenteJoueurs.sceneAttente(primaryStage, AttenteJoueurs.getLevelNum());
                });

            if(AttenteJoueurs.isJoueur1Connecte() && AttenteJoueurs.isJoueur2Connecte()){
                try {
                    AttenteJoueurs.updateConnectedClients();
                    AppSAE.setSceneAttente(AppSAE.getScene());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }}
        });

        server.addEventListener("chat", String.class, (client, data, ackSender) -> {
            System.out.println("Message received from client: " + data);
            AttenteJoueurs.setJoueur1Connecte(true);
            AttenteJoueurs.setJoueur2Connecte(true);
        });

        server.addEventListener("playerMovement", MouvementJoueur.class, (client, data, ackSender) -> {
            System.out.println("Received player movement from client: " + data.getPlayerId());
            // Diffusez les mouvements du joueur à tous les autres clients
            server.getBroadcastOperations().sendEvent("playerMovement", data);
        });

        server.addEventListener("mess", String.class, (client, data, ackSender) -> {
            System.out.println("Message received from client: " + data);
        });

        Thread serverThread = new Thread(() ->{
            server.start();
            System.out.println("SocketIO server started");

        });

        serverThread.start();
    }
}
