package com.feueau.datas;

import java.sql.*;

public class RecupIDJoueur {

    public static int RecupIPAvecPseudo(String NomUtilisateur) {

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

            String sql = "SELECT ID FROM player WHERE Username = ?";
            int resID = 0;
            try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                statement.setString(1, NomUtilisateur);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        resID = resultSet.getInt(1);
                        System.out.println(resID);
                        //return resID;
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

        return 0;
    }

    public static void main(String[] args){
        int a = RecupIPAvecPseudo("remi");
        System.out.println(a);
    }

}
