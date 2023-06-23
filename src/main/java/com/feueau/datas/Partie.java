package com.feueau.datas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Partie {

    public static void AjoutPartie(String nomPartie, String motDePassePartie, int Joueur1, int Joueur2, int NumNiv) {
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

            String sql1 = "SELECT COUNT(*) FROM partie WHERE Nom = ?";
            int count1=0;
            try (PreparedStatement statement = connexion.prepareStatement(sql1)) {
                statement.setString(1, nomPartie);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        count1 = resultSet.getInt(1);
                    }
                }
            }

            if (count1 >= 1) {
                String sql2 = "SELECT COUNT(*) FROM partie WHERE ID_Joueur2 = ?";
                int count2=0;
                try (PreparedStatement statement = connexion.prepareStatement(sql2)) {
                    statement.setInt(1, Joueur2);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            count2 = resultSet.getInt(1);
                        }
                    }
                }

                if (count2 >= 1) {
                    String sql3 = "UPDATE partie SET Num_Niveau = ? WHERE Nom = ?";
                    try (PreparedStatement statement = connexion.prepareStatement(sql3)) {
                        statement.setInt(1, NumNiv);
                        statement.setString(2, nomPartie);
                        statement.executeUpdate();
                        System.out.println("Mise à jour réussie de la colonne NumNiv.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    String sql4 = "UPDATE partie SET ID_Joueur2 = ? WHERE Nom = ?";
                    try (PreparedStatement statement = connexion.prepareStatement(sql4)) {
                        statement.setInt(1, Joueur2);
                        statement.setString(2, nomPartie);
                        statement.executeUpdate();
                        System.out.println("Mise à jour réussie de la colonne ID_Joueur2.");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                String sql2 = "INSERT INTO partie (Nom, Mdp,ID_Joueur1) VALUES (?, ?, ?)";
                try (PreparedStatement statement = connexion.prepareStatement(sql2)) {
                    statement.setString(1, nomPartie);
                    statement.setString(2, motDePassePartie);
                    statement.setInt(3, Joueur1);
                    statement.executeUpdate();
                    System.out.println("Utilisateur ajouté avec succès à la base de données.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
