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

            this.grille[14][1] = new Bloc("bloc", true);
            this.grille[14][2] = new Bloc("bloc", true);
            this.grille[14][3] = new Bloc("bloc", true);
            this.grille[14][4] = new Bloc("bloc", true);
            this.grille[14][5] = new Bloc("bloc", true);
            this.grille[14][6] = new Bloc("bloc", true);
            this.grille[14][7] = new Bloc("bloc", true);
            this.grille[14][8] = new Bloc("bloc", true);
            this.grille[14][9] = new Bloc("bloc", true);
            this.grille[14][10] = new Bloc("bloc", true);
            this.grille[14][11] = new Bloc("bloc", true);
            this.grille[14][12] = new Bloc("bloc", true);
            this.grille[14][13] = new Bloc("bloc", true);
            this.grille[14][14] = new Bloc("bloc", true);
            this.grille[14][15] = new Bloc("bloc", true);
            this.grille[14][16] = new Bloc("bloc", true);
            this.grille[14][17] = new Bloc("bloc", true);
            this.grille[14][18] = new Bloc("bloc", true);

            this.grille[13][1] = new Bloc("bloc", true);
            this.grille[13][2] = new Bloc("bloc", true);
            this.grille[12][1] = new Bloc("bloc", true);

            this.grille[11][4] = new Bloc("bloc", true);
            this.grille[11][5] = new Bloc("bloc", true);
            this.grille[11][6] = new Bloc("bloc", true);
            this.grille[11][7] = new Bloc("bloc", true);
            this.grille[11][8] = new Bloc("bloc", true);
            this.grille[11][9] = new Bloc("bloc", true);
            this.grille[11][10] = new Bloc("bloc", true);
            this.grille[11][11] = new Bloc("bloc", true);
            this.grille[11][12] = new Bloc("bloc", true);
            this.grille[11][13] = new Bloc("bloc", true);
            this.grille[11][14] = new Bloc("bloc", true);
            this.grille[11][15] = new Bloc("bloc", true);

            this.grille[10][15] = new Bloc("bloc", true);
            this.grille[9][15] = new Bloc("bloc", true);
            this.grille[8][15] = new Bloc("bloc", true);
            this.grille[11][10] = new Bloc("bloc", true);
            this.grille[10][12] = new Bloc("bloc", true);
            this.grille[9][13] = new Bloc("bloc", true);

            this.grille[8][14] = new Bloc("bloc", true);
            this.grille[8][15] = new Bloc("bloc", true);
            this.grille[8][16] = new Bloc("bloc", true);
            this.grille[8][17] = new Bloc("bloc", true);
            this.grille[8][18] = new Bloc("bloc", true);
            this.grille[8][19] = new Bloc("bloc", true);
            this.grille[8][20] = new Bloc("bloc", true);
            this.grille[8][21] = new Bloc("bloc", true);
            this.grille[8][22] = new Bloc("bloc", true);
            this.grille[8][23] = new Bloc("bloc", true);
            this.grille[8][24] = new Bloc("bloc", true);
            this.grille[8][25] = new Bloc("bloc", true);
            this.grille[8][26] = new Bloc("bloc", true);
            this.grille[8][27] = new Bloc("bloc", true);
            this.grille[8][28] = new Bloc("bloc", true);
            this.grille[8][29] = new Bloc("bloc", true);
            this.grille[8][30] = new Bloc("bloc", true);

            this.grille[7][30] = new Bloc("bloc", true);
            this.grille[6][30] = new Bloc("bloc", true);
            this.grille[5][30] = new Bloc("bloc", true);
            this.grille[4][30] = new Bloc("bloc", true);

            this.grille[7][29] = new Bloc("bloc", true);
            this.grille[6][29] = new Bloc("bloc", true);
            this.grille[5][29] = new Bloc("bloc", true);

            this.grille[7][28] = new Bloc("bloc", true);
            this.grille[6][28] = new Bloc("bloc", true);

            this.grille[7][27] = new Bloc("bloc", true);

            this.grille[3][30] = new Bloc("bloc", true);
            this.grille[3][29] = new Bloc("bloc", true);
            this.grille[3][28] = new Bloc("bloc", true);
            this.grille[3][27] = new Bloc("bloc", true);
            this.grille[3][26] = new Bloc("bloc", true);
            this.grille[3][25] = new Bloc("bloc", true);
            this.grille[3][24] = new Bloc("bloc", true);
            this.grille[3][23] = new Bloc("bloc", true);
            this.grille[3][22] = new Bloc("bloc", true);
            this.grille[3][21] = new Bloc("bloc", true);
            this.grille[3][20] = new Bloc("bloc", true);

            this.grille[7][20] = new Bloc("bloc", true);
            this.grille[6][20] = new Bloc("bloc", true);
            this.grille[7][21] = new Bloc("bloc", true);
            this.grille[7][19] = new Bloc("bloc", true);
            this.grille[8][19] = new Bloc("bloc", true);

            this.grille[5][17] = new Bloc("bloc", true);
            this.grille[5][16] = new Bloc("bloc", true);
            this.grille[5][15] = new Bloc("bloc", true);
            this.grille[5][14] = new Bloc("bloc", true);

            this.grille[4][15] = new Bloc("bloc", true);
            this.grille[3][18] = new Bloc("bloc", true);
            this.grille[3][19] = new Bloc("bloc", true);

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

            this.grille[7][10] = new Bloc("bloc", true);
            this.grille[7][9] = new Bloc("bloc", true);
            this.grille[7][8] = new Bloc("bloc", true);
            this.grille[7][7] = new Bloc("bloc", true);

            this.grille[6][9] = new Bloc("bloc", true);
            this.grille[6][8] = new Bloc("bloc", true);
            this.grille[6][7] = new Bloc("bloc", true);

            this.grille[5][8] = new Bloc("bloc", true);
            this.grille[5][7] = new Bloc("bloc", true);

            this.grille[4][7] = new Bloc("bloc", true);

            this.grille[3][12] = new Bloc("bloc", true);
            this.grille[3][11] = new Bloc("bloc", true);
            this.grille[3][10] = new Bloc("bloc", true);



            this.grille[14][23] = new Bloc("bloc", true);

            this.grille[13][22] = new Bloc("bloc", true);

            this.grille[12][19] = new Bloc("bloc", true);
            this.grille[12][20] = new Bloc("bloc", true);
            this.grille[12][21] = new Bloc("bloc", true);

            this.grille[2][29] = new Bloc("porteFinFeu", false);
            this.grille[2][30] = new Bloc("porteFinEau", false);
        }

        if (nom == "Level 2") {
            this.xJoueur1 = new BigDecimal("1.0");
            this.yJoueur1 = new BigDecimal("16.0");
            this.xJoueur2 = new BigDecimal("2.0");
            this.yJoueur2 = new BigDecimal("16.0");

            this.grille[17][5] = new Bloc( "feu", true);
            this.grille[17][6] = new Bloc( "feu", true);

            this.grille[17][8] = new Bloc( "eau", true);
            this.grille[17][9] = new Bloc( "eau", true);

            this.grille[8][1] = new Bloc("bloc", true);
            this.grille[9][1] = new Bloc("bloc", true);
            this.grille[9][2] = new Bloc("bloc", true);
            this.grille[10][3] = new Bloc("bloc", true);
            this.grille[10][4] = new Bloc("bloc", true);
            this.grille[10][5] = new Bloc("bloc", true);
            this.grille[11][6] = new Bloc("bloc", true);
            this.grille[11][7] = new Bloc("bloc", true);
            this.grille[12][8] = new Bloc("bloc", true);
            this.grille[12][9] = new Bloc("bloc", true);
            this.grille[13][10] = new Bloc("bloc", true);
            this.grille[13][11] = new Bloc("bloc", true);
            this.grille[14][12] = new Bloc("bloc", true);
            this.grille[14][13] = new Bloc("bloc", true);

            this.grille[15][16] = new Bloc("bloc", true);
            this.grille[16][16] = new Bloc("bloc", true);
            this.grille[16][15] = new Bloc("bloc", true);

            for (int i=0;i<8;i++) {
                this.grille[16-i][17] = new Bloc("bloc", true);
            }

            this.grille[8][16] = new Bloc("bloc", true);

            this.grille[7][4] = new Bloc("bloc", true);
            this.grille[7][5] = new Bloc("bloc", true);
            this.grille[7][6] = new Bloc("eau", true);
            this.grille[7][7] = new Bloc("eau", true);
            this.grille[7][8] = new Bloc("feu", true);
            this.grille[7][9] = new Bloc("feu", true);
            this.grille[7][10] = new Bloc("eau", true);
            this.grille[7][11] = new Bloc("eau", true);
            this.grille[7][12] = new Bloc("bloc", true);
            this.grille[7][13] = new Bloc("bloc", true);
            this.grille[7][14] = new Bloc("bloc", true);
            this.grille[7][15] = new Bloc("bloc", true);

            this.grille[17][18] = new Bloc("feu", true);
            this.grille[17][19] = new Bloc("feu", true);
            this.grille[17][20] = new Bloc("feu", true);
            this.grille[17][21] = new Bloc("feu", true);
            this.grille[17][22] = new Bloc("feu", true);
            this.grille[17][23] = new Bloc("feu", true);
            this.grille[17][24] = new Bloc("feu", true);
            this.grille[17][25] = new Bloc("feu", true);
            this.grille[17][26] = new Bloc("feu", true);
            this.grille[17][27] = new Bloc("feu", true);

            this.grille[12][20] = new Bloc("bloc", true);
            this.grille[13][24] = new Bloc("bloc", true);
            this.grille[15][26] = new Bloc("bloc", true);
            this.grille[16][28] = new Bloc("bloc", true);

            this.grille[16][29] = new Bloc("porteFinFeu", false);
            this.grille[16][30] = new Bloc("porteFinEau", false);
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
