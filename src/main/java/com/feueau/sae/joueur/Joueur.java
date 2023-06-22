package com.feueau.sae.joueur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Joueur {

    private Double y=1.0;
    private Double x=1.0;
    private String type;

    private String pathImgDroit;
    private String pathImgGauche;
    public Joueur() {
    }
    public Joueur(Double y, Double x, String type) {
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

    public Double getY() {
        return y;
    }
    public Double getX() {
        return x;
    }

    public void gauche() {
        this.x = ((this.x)*10-1)/10.0;
    }

    public void droite() {
        this.x = ((this.x)*10+1)/10.0;
    }

    public void haut() {
        this.y = ((this.y)*10-1)/10.0;
    }

    public void bas() {
        this.y = ((this.y)*10+1)/10.0;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
