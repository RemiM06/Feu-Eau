package com.feueau.datas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;

public class VerifConnexion {

    public static int Verif(String NomUtilisateur, String MotDePasse) {

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

            String sql = "SELECT Mdp FROM player WHERE Username = ?";
            String resMdp = null;
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, NomUtilisateur);
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