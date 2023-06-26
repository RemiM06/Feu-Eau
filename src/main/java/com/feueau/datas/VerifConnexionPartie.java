package com.feueau.datas;

import java.sql.*;
import java.util.Objects;

public class VerifConnexionPartie {

    public static int Verif(String Nom, String MotDePasse) {

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

            String sql = "SELECT Mdp FROM partie WHERE Nom = ?";
            String resMdp = null;
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, Nom);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        resMdp = resultSet.getString(1);
                    }
                }
            }

            if (Objects.equals(resMdp, MotDePasse)) {
                return 1;
            } else {
                return 0;
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
