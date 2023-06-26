package com.feueau.datas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MAJNumNiveau {

    public static void AjoutPC(String nomPartie, int NumNiv) {

        String url = "jdbc:mysql://134.59.143.50:3306/sae_feueau";
        String utilisateurBDD = "mr012420";
        String motDePasseBDD = "Rqznu7ey";

        Connection connexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connexion = DriverManager.getConnection(url, utilisateurBDD, motDePasseBDD);

            String sql4 = "UPDATE partie SET Num_Niveau = ? WHERE Nom = ?";
            try (PreparedStatement statement = connexion.prepareStatement(sql4)) {
                statement.setInt(1, NumNiv);
                statement.setString(2, nomPartie);
                statement.executeUpdate();
                System.out.println("Mise à jour réussie de la colonne NumNiv.");
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
