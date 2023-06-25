package com.feueau.network;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

public class Client {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("25.73.214.239");
        config.setPort(1234);

        final SocketIOServer server = new SocketIOServer(config);
        SocketIONamespace namespace = server.addNamespace("/");

        namespace.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("Connected to the server");
                client.sendEvent("message", "Client connected successfully");
            }
        });

        server.start();
    }
}
