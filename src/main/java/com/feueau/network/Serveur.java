package com.feueau.network;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.feueau.sae.menus.composants.AttenteJoueurs;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

import static com.feueau.sae.menus.composants.AttenteJoueurs.updateConnectedClients;


public class Serveur {

    private static SocketIOServer serverSocket;
    private static List<SocketIOClient> connectedClients = new ArrayList<>();

    private static final String SERVER_ID = "server";

    public static List<SocketIOClient> getConnectedClients(){
        return connectedClients;
    }
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("25.31.110.196");
        config.setPort(1234);

        final SocketIOServer server = new SocketIOServer(config);


        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {

                System.out.println("A client has connected");
                connectedClients.add(client);
                AttenteJoueurs.setJoueur2Connecte(true);

                try {
                    updateConnectedClients();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        server.addEventListener("chat", String.class, (client, data, ackSender) -> {
            System.out.println("Message received from client: " + data);
            AttenteJoueurs.setJoueur1Connecte(true);
            AttenteJoueurs.setJoueur2Connecte(true);

        });

        server.start();
    }
}
