package com.feueau.sae.joueur;

public class Joueur {

    private double y=1.0;
    private double x=1.0;

    public Joueur() {
    }
    public Joueur(double y, double x) {
        this.y=y;
        this.x=x;
    }
    public double getY() {
        return y;
    }
    public double getX() {
        return x;
    }

    public void gauche() {
        this.x = ((this.x*10) - 1)/10.0;
    }

    public void droite() {
        this.x = ((this.x*10) + 1)/10.0;
    }

    public void haut() {
        this.y = ((this.y*10) + 1)/10.0;
    }

    public void bas() {
        this.y = ((this.y*10) - 1)/10.0;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
