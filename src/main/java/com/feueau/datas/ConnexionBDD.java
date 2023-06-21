/*package com.feueau.datas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConnexionBDD {

    static Connection connexion=null;
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sae_feueau";
        String utilisateur = "root";
        String motDePasse = "";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie à la base de données MySQL!");

            // Effectuez vos opérations sur la base de données ici

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
    }

    public static Connection getConnection(){
        return connexion;
    }

}
*/
