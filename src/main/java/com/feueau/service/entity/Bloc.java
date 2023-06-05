package com.feueau.service.entity;

import javafx.scene.paint.Color;

public class Bloc {

    private int x;
    private int y;
    private String name;
    private boolean etat;
    private Color couleur;

    public Bloc(int x, int y, String name, boolean etat, Color couleur) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.etat = etat;
        this.couleur = couleur;
    }

    public void changementEtat() {

        this.etat = !this.etat;
    }

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
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
}
