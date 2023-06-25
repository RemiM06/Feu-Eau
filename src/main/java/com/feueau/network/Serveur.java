package com.feueau.network;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;

public class Serveur {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("25.73.216.239");
        config.setPort(1234);

        final SocketIOServer server = new SocketIOServer(config);

        server.addConnectListener(new ConnectListener() {
            @Override
            public void onConnect(SocketIOClient client) {
                System.out.println("A client has connected");
            }
        });

        server.start();
    }
}
