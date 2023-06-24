package com.feueau.datas;

import java.sql.*;
import java.util.Objects;

public class RecupIP {

    public static String RecupIPAvecPseudo(String NomUtilisateur) {

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

            String sql = "SELECT IP FROM player WHERE Username = ?";
            String resIP = null;
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, NomUtilisateur);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        resIP = resultSet.getString(1);
                        return resIP;
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
