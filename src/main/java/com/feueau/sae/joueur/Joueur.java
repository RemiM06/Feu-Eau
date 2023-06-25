package com.feueau.sae.joueur;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.math.BigDecimal;

public class Joueur {

    private BigDecimal y = new BigDecimal("1.0");
    private BigDecimal x = new BigDecimal("1.0");
    private BigDecimal xVelocity = new BigDecimal("0.0");
    private BigDecimal yVelocity = new BigDecimal("0.0");
    private String type;
    private boolean isJumping = false;
    private String pathImgDroit;
    private String pathImgGauche;
    public Joueur() {

    }
    public Joueur(BigDecimal y, BigDecimal x, String type) {
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
            yVelocity = new BigDecimal("-12.0");
        }
        else {
            yVelocity = new BigDecimal("0.0");
        }
        isJumping = jumping;
    }

    public BigDecimal getxVelocity() {
        return xVelocity;
    }

    public BigDecimal getyVelocity() {
        return yVelocity;
    }

    public void setxVelocity(BigDecimal xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setyVelocity(BigDecimal yVelocity) {
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

    public BigDecimal getY() {
        return y;
    }
    public BigDecimal getX() {
        return x;
    }
    public void setX(BigDecimal nb) {
        this.x = this.x.add(nb);
        /*System.out.println("--------------");
        System.out.print("x : ");
        System.out.println(this.x);*/
    }

    public void setY(BigDecimal nb) {
        this.y = nb;
        /*System.out.println("--------------");
        System.out.print("y : ");
        System.out.println(this.y);*/
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "y=" + y +
                ", x=" + x +
                ", xVelocity=" + xVelocity +
                ", yVelocity=" + yVelocity +
                ", type='" + type + '\'' +
                ", isJumping=" + isJumping +
                ", pathImgDroit='" + pathImgDroit + '\'' +
                ", pathImgGauche='" + pathImgGauche + '\'' +
                '}';
    }
}
