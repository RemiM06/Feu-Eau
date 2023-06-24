package com.feueau.network;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        Socket clientSocket = null;

        try {
            clientSocket = new Socket("192.168.56.1", 1234);
        } catch (UnknownHostException e) {
            System.out.println("Adresse IP du serveur inconnue.");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Erreur lors de la connexion au serveur.");
            System.exit(1);
        }

        try {
            // Créer les flux d'entrée/sortie pour communiquer avec le serveur
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Demander et envoyer le nom du joueur au serveur
            System.out.print("Entrez votre nom : ");
            Scanner scanner = new Scanner(System.in);
            String nomJoueur = scanner.nextLine();
            output.println(nomJoueur);

            // Lire et afficher le message de bienvenue du serveur
            String messageBienvenue = input.readLine();
            System.out.println("Serveur : " + messageBienvenue);

            // Thread pour recevoir les messages des autres joueurs
            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String message;
                        while ((message = input.readLine()) != null) {
                            System.out.println(message);
                        }
                    } catch (IOException e) {
                        System.out.println("Erreur lors de la réception du message du serveur.");
                        System.exit(1);
                    }
                }
            });
            receiveThread.start();

            // Envoyer les messages au serveur
            while (true) {
                String message = scanner.nextLine();
                output.println(message);
            }

        } catch (IOException e) {
            System.out.println("Erreur lors de la communication avec le serveur.");
            System.exit(1);
        } finally {
            try {
                // Fermer les flux et le socket
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Erreur lors de la fermeture du socket client.");
            }
        }
    }
}
