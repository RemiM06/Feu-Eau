package com.feueau.sae.level;

import com.feueau.service.entity.Bloc;
import javafx.scene.paint.Color;

public class Level {
    private int nombreRow=18;
    private int nombreCol=32;
    private Bloc[][] grille = new Bloc[this.nombreRow][this.nombreCol];

    private int xJoueur1;
    private int yJoueur1;
    private int xJoueur2;
    private int yJoueur2;

    public Level(String nom) {
        for (int row = 0; row < this.nombreRow; row++) {
            for (int col = 0; col < this.nombreCol; col++) {
                if ((row == 0 || row == this.nombreRow - 1) || (col == 0 || col == this.nombreCol - 1)) {
                    this.grille[row][col] = new Bloc(row, col, "bloc", true);
                } else {
                    this.grille[row][col] = new Bloc(row, col, "vide", false);
                }
            }
        }

        if (nom == "Level 1") {
            this.xJoueur1 = 1;
            this.yJoueur1 = 16;
            this.xJoueur2 = 2;
            this.yJoueur2 = 16;

            this.grille[16][26] = new Bloc(16, 30, "bloc", true);
            this.grille[16][27] = new Bloc(16, 30, "bloc", true);
            this.grille[16][28] = new Bloc(16, 30, "bloc", true);
            this.grille[16][29] = new Bloc(16, 30, "bloc", true);
            this.grille[16][30] = new Bloc(16, 30, "bloc", true);

            this.grille[15][27] = new Bloc(16, 30, "bloc", true);
            this.grille[15][28] = new Bloc(16, 30, "bloc", true);
            this.grille[15][29] = new Bloc(16, 30, "bloc", true);
            this.grille[15][30] = new Bloc(16, 30, "bloc", true);

            this.grille[14][28] = new Bloc(16, 30, "bloc", true);
            this.grille[14][29] = new Bloc(16, 30, "bloc", true);
            this.grille[14][30] = new Bloc(16, 30, "bloc", true);

            this.grille[1][29] = new Bloc(1,29,"porteFinFeu", false);
            this.grille[1][30] = new Bloc(1,30,"porteFinEau", false);
        }
    }

    public int getNombreRow() {
        return nombreRow;
    }

    public int getNombreCol() {
        return nombreCol;
    }

    public Bloc[][] getGrille() {
        return grille;
    }

    public int getxJoueur1() {
        return xJoueur1;
    }

    public int getyJoueur1() {
        return yJoueur1;
    }

    public int getxJoueur2() {
        return xJoueur2;
    }

    public int getyJoueur2() {
        return yJoueur2;
    }

    public String toString() {
        for (int col = 0; col < this.nombreCol; col++) {
            for (int row = 0; row < this.nombreRow; row++) {
                /*if ((joueur1.getY() == row) && (joueur1.getX() == col)) {
                    System.out.print(" j " + " ");
                }
                else {
                    System.out.print(this.grille[col][row] + " ");
                }*/
                System.out.print(this.grille[col][row] + " ");
            }
            System.out.println();
        }
        return "";
    }
}
