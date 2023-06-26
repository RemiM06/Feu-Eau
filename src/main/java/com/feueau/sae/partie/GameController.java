package com.feueau.sae.partie;

import com.feueau.sae.joueur.Joueur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*public class GameController {

    private Server server;
    private Client client;
    private Partie partie;

    public GameController(Partie partie) {
        this.partie = partie;
        this.server = null;
        this.client = null;
    }

    public void startServer(int port) throws IOException {
        this.server = new Server(port);
        server.start();
    }

    public void startClient(String host, int port) throws IOException {
        this.client = new Client(host, port);
        client.start();
    }

    public void sendPlayerMove(Joueur joueur) throws IOException {
        String move = createMoveString(joueur);
        if(server != null) {
            server.sendMessage(move);
        }
        if(client != null) {
            client.sendMessage(move);
        }
    }

    public String createMoveString(Joueur joueur) {
        return joueur.getX().toString() + ";" + joueur.getY().toString();
    }

    public void applyMove(String move, Joueur joueur) {
        String[] parts = move.split(";");
        joueur.setX(new BigDecimal(parts[0]));
        joueur.setY(new BigDecimal(parts[1]));
    }
}*/

