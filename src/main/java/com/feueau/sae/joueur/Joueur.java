package com.feueau.sae.joueur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Joueur {

    private int y=1;
    private int x=1;
    private String type;

    private String pathImgDroit;
    private String pathImgGauche;
    public Joueur() {
    }
    public Joueur(int y, int x, String type) {
        this.y=y;
        this.x=x;
        this.type=type;
        if (type == "feu") {
            this.pathImgDroit = getClass().getResource("/img/Akainu-droite.png").toExternalForm();
            this.pathImgGauche =  getClass().getResource("/img/Akainu-gauche.png").toExternalForm();
        }
        if (type == "eau") {
            this.pathImgDroit = getClass().getResource("/img/Aokiji-droite-eau.png").toExternalForm();
            this.pathImgGauche = getClass().getResource("/img/Aokiji-gauche-eau.png").toExternalForm();
        }
    }
    public String getType() {
        return type;
    }

    public String getPathImgDroit() {
        return pathImgDroit;
    }

    public String getPathImgGauche() {
        return pathImgGauche;
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
