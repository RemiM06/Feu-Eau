package com.feueau.sae.level;

import com.feueau.sae.joueur.Joueur;
import com.feueau.service.entity.Bloc;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;

public class LEVEL1 {

    private String name = "Niveau 1";
    private int tailleRow=10;
    private int tailleCol=10;
    private Bloc[][] grille = new Bloc[this.tailleRow][this.tailleCol];

    private Joueur joueur1 = new Joueur(1,8);

    public LEVEL1() {
        for (int row = 0; row < this.tailleRow; row++) {
            for (int col = 0; col < this.tailleRow; col++) {
                if ((row == 0 || row == this.tailleRow - 1) || (col == 0 || col == this.tailleCol - 1)) {
                    this.grille[row][col] = new Bloc(row, col, "bloc", true, Color.BLACK);
                } else {
                    this.grille[row][col] = new Bloc(row, col, "bloc", false, Color.BLACK);
                }
            }
        }
        this.grille[8][8] = new Bloc(8, 8, "bloc", true, Color.BLACK);
        this.grille[7][8] = new Bloc(7, 8, "bloc", true, Color.BLACK);
        this.grille[8][7] = new Bloc(8, 7, "bloc", true, Color.BLACK);
        this.grille[7][7] = new Bloc(7, 7, "bloc", true, Color.BLACK);
        this.grille[6][8] = new Bloc(6, 8, "bloc", true, Color.BLACK);
        this.grille[8][6] = new Bloc(8, 6, "bloc", true, Color.BLACK);
        this.grille[5][3] = new Bloc(5, 3, "bloc", true, Color.BLACK);
        this.grille[5][4] = new Bloc(5, 4, "bloc", true, Color.BLACK);
        this.grille[5][5] = new Bloc(5, 5, "bloc", true, Color.BLACK);
        this.grille[5][6] = new Bloc(5, 6, "bloc", true, Color.BLACK);
        System.out.println(this);
        System.out.println("fin");
    }


    //faire une classe niveau générique dont les autres niveaux heriteront


}
