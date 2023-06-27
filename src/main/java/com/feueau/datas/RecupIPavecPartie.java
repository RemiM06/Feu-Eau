package com.feueau.datas;

import java.sql.*;

public class RecupIPavecPartie {

    public static String RecupIP(String NomPartie) {

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

            String sql = "SELECT ID_Joueur1 FROM partie WHERE Nom = ?";
            String res = null;
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, NomPartie);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        res = resultSet.getString(1);
                    }
                }
            }

            String sql2 = "SELECT IP FROM player WHERE ID = ?";
            String res2 = null;
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, res);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        res2 = resultSet.getString(1);
                        return res2;
                    }
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

        return null;
    }

}
