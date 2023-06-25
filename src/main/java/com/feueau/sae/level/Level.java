package com.feueau.sae.level;

import com.feueau.service.entity.Bloc;
import javafx.scene.paint.Color;

import java.math.BigDecimal;

public class Level {
    private int nombreRow=18;
    private int nombreCol=32;
    private Bloc[][] grille = new Bloc[this.nombreRow][this.nombreCol];

    private BigDecimal xJoueur1;
    private BigDecimal yJoueur1;
    private BigDecimal xJoueur2;
    private BigDecimal yJoueur2;

    public Level(String nom) {
        this.xJoueur1 = new BigDecimal("1.0");
        this.yJoueur1 = new BigDecimal("16.0");
        this.xJoueur2 = new BigDecimal("2.0");
        this.yJoueur2 = new BigDecimal("16.0");

        for (int row = 0; row < this.nombreRow; row++) {
            for (int col = 0; col < this.nombreCol; col++) {
                if ((row == 0 || row == this.nombreRow - 1) || (col == 0 || col == this.nombreCol - 1)) {
                    this.grille[row][col] = new Bloc("bloc", true);
                } else {
                    this.grille[row][col] = new Bloc("vide", false);
                }
            }
        }
        if (nom == "Level 1") {
            this.xJoueur1 = new BigDecimal("1.0");
            this.yJoueur1 = new BigDecimal("16.0");
            this.xJoueur2 = new BigDecimal("2.0");
            this.yJoueur2 = new BigDecimal("16.0");

            this.grille[15][3] = new Bloc("bloc", true);
            this.grille[14][5] = new Bloc("bloc", true);
            this.grille[13][7] = new Bloc("bloc", true);

            this.grille[16][26] = new Bloc("bloc", true);
            this.grille[16][27] = new Bloc("bloc", true);
            this.grille[16][28] = new Bloc("bloc", true);
            this.grille[16][29] = new Bloc("bloc", true);
            this.grille[16][30] = new Bloc("bloc", true);

            this.grille[15][27] = new Bloc("bloc", true);
            this.grille[15][28] = new Bloc("bloc", true);
            this.grille[15][29] = new Bloc("bloc", true);
            this.grille[15][30] = new Bloc("bloc", true);

            this.grille[14][28] = new Bloc("bloc", true);
            this.grille[14][29] = new Bloc("bloc", true);
            this.grille[14][30] = new Bloc("bloc", true);


            this.grille[14][23] = new Bloc("bloc", true);

            this.grille[13][22] = new Bloc("bloc", true);

            this.grille[12][19] = new Bloc("bloc", true);
            this.grille[12][20] = new Bloc("bloc", true);
            this.grille[12][21] = new Bloc("bloc", true);

            this.grille[1][29] = new Bloc("porteFinFeu", false);
            this.grille[1][30] = new Bloc("porteFinEau", false);
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

    public BigDecimal getxJoueur1() {
        return xJoueur1;
    }

    public BigDecimal getyJoueur1() {
        return yJoueur1;
    }

    public BigDecimal getxJoueur2() {
        return xJoueur2;
    }

    public BigDecimal getyJoueur2() {
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
