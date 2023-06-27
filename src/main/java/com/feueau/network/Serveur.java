package com.feueau.network;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.feueau.network.recuperation.IPUtilisateur;
import com.feueau.sae.AppSAE;
import com.feueau.sae.menus.composants.AttenteJoueurs;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static com.feueau.sae.AppSAE.primaryStage;


public class Serveur {

    public static SocketIOServer serverSocket;

    private static Consumer<String> messageListener;

    private static List<SocketIOClient> connectedClients = new ArrayList<>();

    public static List<SocketIOClient> getConnectedClients(){
        return connectedClients;
    }
    public static void main(String[] args) {
        Configuration config = new Configuration();
        String monIp = IPUtilisateur.getIPAddress();
        config.setHostname(monIp);
        config.setPort(1234);




        serverSocket = new SocketIOServer(config);


        serverSocket.addConnectListener(new ConnectListener() {
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

        serverSocket.addEventListener("chat", String.class, (client, data, ackSender) -> {
            System.out.println("Message received from client: " + data);
            AttenteJoueurs.setJoueur1Connecte(true);
            AttenteJoueurs.setJoueur2Connecte(true);

        });


        serverSocket.addEventListener("mess", String.class, (client, data, ackSender) -> {
            if (messageListener != null) {
                messageListener.accept(data);
            }
        });

        Thread serverThread = new Thread(() ->{
            serverSocket.start();
            System.out.println("SocketIO server started");

        });

        serverThread.start();
    }
}