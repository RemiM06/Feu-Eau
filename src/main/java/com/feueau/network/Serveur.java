package com.feueau.network;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Serveur {
    private static List<ServeurThread> threads = new ArrayList<>();

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            // Créer un socket serveur et écouter sur le port 1234
            serverSocket = new ServerSocket(1234, 0, InetAddress.getLocalHost());
        } catch (IOException e) {
            System.out.println("Erreur lors de la création du socket serveur.");
            System.exit(1);
        }

        while (true) {
            try {
                // Attendre que les joueurs se connectent
                Socket clientSocket = serverSocket.accept();

                // Créer un nouveau thread pour gérer la communication avec ce joueur
                ServeurThread thread = new ServeurThread(clientSocket, threads);
                threads.add(thread);
                Thread t = new Thread(thread);
                t.start();
            } catch (IOException e) {
                System.out.println("Erreur lors de l'acceptation de la connexion d'un joueur.");
                System.exit(1);
            }
        }
    }
}

class ServeurThread implements Runnable {
    private Socket clientSocket;
    private BufferedReader input;
    private PrintWriter output;
    private List<ServeurThread> threads;

    public ServeurThread(Socket clientSocket, List<ServeurThread> threads) {
        this.clientSocket = clientSocket;
        this.threads = threads;
    }

    public void run() {
        try {
            // Créer les flux d'entrée/sortie pour communiquer avec le joueur
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Recevoir le nom du joueur
            String nomJoueur = input.readLine();
            System.out.println(nomJoueur + " s'est connecté.");

            // Envoyer un message de bienvenue à ce joueur
            output.println("Bienvenue, " + nomJoueur + "!");

            // Recevoir et envoyer des messages aux autres joueurs
            while (true) {
                String message = input.readLine();
                if (message == null) {
                    // Le joueur a quitté la partie
                    break;
                }

                // Envoyer le message à tous les autres joueurs connectés
                envoyerMessage(nomJoueur, message);
            }

            // Fermer les flux et le socket
            input.close();
            output.close();
            clientSocket.close();

            System.out.println(nomJoueur + " a quitté la partie.");
            threads.remove(this);
        } catch (IOException e) {
            System.out.println("Erreur lors de la communication avec un joueur.");
            System.exit(1);
        }
    }

    private void envoyerMessage(String nomJoueur, String message) {
        // Parcourir la liste des joueurs connectés et envoyer le message à tous les autres
        for (ServeurThread thread : threads) {
            if (thread != this) {
                thread.output.println(nomJoueur + ": " + message);
            }
        }

        // Afficher le message dans la console du serveur
        System.out.println(nomJoueur + ": " + message);
    }
}
