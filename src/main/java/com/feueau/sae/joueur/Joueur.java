package com.feueau.sae.joueur;

public class Joueur {

    private int y=1;
    private int x=1;
    private String type;

    public Joueur() {
    }
    public Joueur(int y, int x, String type) {
        this.y=y;
        this.x=x;
        this.type=type;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public void gauche() {
        this.x = this.x-1;
    }

    public void droite() {
        this.x = this.x+1;
    }

    public void haut() {
        this.y = this.y-1;
    }

    public void bas() {
        this.y = this.y+1;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
