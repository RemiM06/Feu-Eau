    package com.feueau.network;

    import com.corundumstudio.socketio.Configuration;
    import com.corundumstudio.socketio.SocketIOClient;
    import com.corundumstudio.socketio.SocketIOServer;
    import com.corundumstudio.socketio.listener.ConnectListener;
    import com.fasterxml.jackson.core.JsonProcessingException;
    import com.feueau.sae.AppSAE;
    import com.feueau.sae.joueur.Joueur;
    import com.feueau.sae.menus.composants.AttenteJoueurs;
    import javafx.application.Platform;
    import javafx.stage.Stage;

    import java.io.DataInputStream;
    import java.io.DataOutputStream;
    import java.io.IOException;
    import java.math.BigDecimal;
    import java.net.ServerSocket;
    import java.net.Socket;
    import java.util.ArrayList;
    import java.util.List;

    import static com.feueau.sae.AppSAE.primaryStage;

    public class Serveur {

        private static SocketIOServer serverSocket;
        private static List<SocketIOClient> connectedClients = new ArrayList<>();

        public static List<SocketIOClient> getConnectedClients() {
            return connectedClients;
        }

        private static Joueur joueur = new Joueur();

        public static void main(String[] args) {
            Configuration config = new Configuration();
            config.setHostname("134.59.143.49");
            config.setPort(1234);

            final SocketIOServer server = new SocketIOServer(config);

            server.addConnectListener(new ConnectListener() {
                @Override
                public void onConnect(SocketIOClient client) {
                    System.out.println("A client has connected");
                    connectedClients.add(client);
                    AttenteJoueurs.setJoueur2Connecte(true);

                    Platform.runLater(() -> {
                        AttenteJoueurs.sceneAttente(primaryStage, AttenteJoueurs.getLevelNum());
                    });

                    if (AttenteJoueurs.isJoueur1Connecte() && AttenteJoueurs.isJoueur2Connecte()) {
                        try {
                            AttenteJoueurs.updateConnectedClients();
                            AppSAE.setSceneAttente(AppSAE.getScene());
                        } catch (JsonProcessingException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            });

            server.start();
            System.out.println("SocketIO server started");

            try {
                ServerSocket serverSocket = new ServerSocket(1235);
                System.out.println("Server started");

                final boolean[] acceptConnections = {true};

                while (acceptConnections[0]) {
                    Socket socket = serverSocket.accept();
                    System.out.println("Client connected: " + socket);

                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                    // Assume player1 is the player object on the server.
                    Thread thread = new Thread(() -> {
                        while (acceptConnections[0]) {
                            try {
                                String data = input.readUTF();
                                String[] playerData = data.split(",");
                                joueur.setX(new BigDecimal(playerData[0]));
                                joueur.setY(new BigDecimal(playerData[1]));
                                joueur.setxVelocity(new BigDecimal(playerData[2]));
                                joueur.setyVelocity(new BigDecimal(playerData[3]));
                            } catch (IOException e) {
                                e.printStackTrace();
                                acceptConnections[0] = false; // Sort de la boucle en cas d'erreur de lecture
                            }
                        }
                    });

                    thread.start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
