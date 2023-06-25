package com.feueau.network;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Socket;


import java.net.URI;
import java.net.URISyntaxException;

public class Client {
    public static void main(String[] args) throws URISyntaxException {
        Socket socket = new Socket("ws://25.73.214.239:1234");
        socket.on(Socket.EVENT_OPEN, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                socket.send("hi");
                socket.close();
            }
        });
        socket.open();
    }
}
