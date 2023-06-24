package com.feueau.sae.joueur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Joueur {

    private Double y=1.0;
    private Double x=1.0;
    private double xVelocity = 0.0;
    private double yVelocity = 0.0;
    private String type;
    private boolean isJumping = false;
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

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        if (jumping) {
            yVelocity = -12.0;
        }
        else {
            yVelocity = 0.0;
        }
        isJumping = jumping;
    }

    public double getxVelocity() {
        return xVelocity;
    }

    public double getyVelocity() {
        return yVelocity;
    }

    public void setxVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
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
    public void setX(Double nb) {
        this.x = ((this.x)*10+nb)/10.0;
    }

    public void setY(Double nb) {
        this.y = ((this.y)*10+nb)/10.0;
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "y=" + y +
                ", x=" + x +
                '}';
    }
}
