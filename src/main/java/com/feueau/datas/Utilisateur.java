package com.feueau.datas;

import java.sql.*;

public class Utilisateur {

    public static void AjoutUtilisateur(String nomUtilisateur, String motDePasse, String IP) {
        String url = "jdbc:mysql://134.59.143.50:3306/sae_feueau";
        String utilisateurBDD = "root";
        String motDePasseBDD = "";

        Connection connexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connexion = DriverManager.getConnection(url, utilisateurBDD, motDePasseBDD);
            String sql = "INSERT INTO player (userName, mdp, IP) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, nomUtilisateur);
                statement.setString(2, motDePasse);
                statement.setString(3, IP);
                statement.executeUpdate();
                System.out.println("Utilisateur ajouté avec succès à la base de données.");

            } catch (SQLException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (connexion != null) {
                        connexion.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}


