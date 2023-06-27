package com.feueau.datas;

import java.sql.*;
import java.util.Objects;

public class RecupIP {

        public static String verifierIdJoueur(String nomPartie, int idJoueur) {
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

                String sql = "SELECT ID_Joueur1, ID_Joueur2 FROM NomTable WHERE Nom = ?";
                try (PreparedStatement statement = connexion.prepareStatement(sql)) {
                    statement.setString(1, nomPartie);
                    try (ResultSet resultSet = statement.executeQuery()) {
                        if (resultSet.next()) {
                            if(idJoueur == resultSet.getInt("ID_Joueur1")) {
                                return "Joueur 1";
                            } else if(idJoueur == resultSet.getInt("ID_Joueur2")) {
                                return "Joueur 2";
                            } else {
                                return "Joueur ID non trouvé dans cette partie";
                            }
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

            return "Partie non trouvée";
        }

}
