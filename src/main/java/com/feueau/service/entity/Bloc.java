package com.feueau.service.entity;

import javafx.scene.paint.Color;

public class Bloc {

    private int y;
    private int x;
    private String name;
    private boolean etat;
    private Color couleur;

    private String imagePath;

    public Bloc(int y, int x, String name, boolean etat, Color couleur) {
        this.y = y;
        this.x = x;
        this.name = name;
        this.etat = etat;
        this.couleur = couleur;
        if (name == "bloc") {
            this.imagePath = getClass().getResource("/img/Blocs-blocs.png").toExternalForm();
        }
        if (name == "vide") {
            this.imagePath = getClass().getResource("/img/Blocs-fonds.png").toExternalForm();
        }
    }

    public void changementEtat() {
        this.etat = !this.etat;
    }

    public boolean isEtat() {
        return etat;
    }

    public Color getCouleur() {
        return couleur;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        if (this.etat) {
            return " " + this.name.substring(0,1) + " ";
        }
        return "   ";
    }
}
