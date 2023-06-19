package com.feueau.sae.level;

import com.feueau.service.entity.Bloc;
import javafx.scene.paint.Color;

public class Level {
    private int tailleRow=10;
    private int tailleCol=10;
    private Bloc[][] grille = new Bloc[this.tailleRow][this.tailleCol];

    private double xJoueur1;
    private double yJoueur1;
    private double xJoueur2;
    private double yJoueur2;

    public Level(String nom) {
        for (int row = 0; row < this.tailleRow; row++) {
            for (int col = 0; col < this.tailleRow; col++) {
                if ((row == 0 || row == this.tailleRow - 1) || (col == 0 || col == this.tailleCol - 1)) {
                    this.grille[row][col] = new Bloc(row, col, "bloc", true, Color.BLACK);
                } else {
                    this.grille[row][col] = new Bloc(row, col, "bloc", false, Color.BLACK);
                }
            }
        }

        if (nom == "Level 1") {
            this.xJoueur1 = 1.0;
            this.yJoueur1 = 1.0;
            this.xJoueur2 = 2.0;
            this.yJoueur2 = 1.0;

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
        }
    }

    public int getTailleRow() {
        return tailleRow;
    }

    public int getTailleCol() {
        return tailleCol;
    }

    public Bloc[][] getGrille() {
        return grille;
    }

    public double getxJoueur1() {
        return xJoueur1;
    }

    public double getyJoueur1() {
        return yJoueur1;
    }

    public double getxJoueur2() {
        return xJoueur2;
    }

    public double getyJoueur2() {
        return yJoueur2;
    }

    public String toString() {
        for (int col = 0; col < this.tailleCol; col++) {
            for (int row = 0; row < this.tailleRow; row++) {
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
