package com.feueau.datas;

import java.sql.*;

public class AjoutPartieServeur {

    public static void AjoutPS(String nomPartie, String motDePassePartie, int Joueur1) {

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

            String sql = "INSERT INTO partie (Nom, Mdp,ID_Joueur1) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, nomPartie);
                statement.setString(2, motDePassePartie);
                statement.setInt(3, Joueur1);
                statement.executeUpdate();
                System.out.println("Partie ajouté avec succès à la base de données.");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
