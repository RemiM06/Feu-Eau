package com.feueau.service.entity;

public class Bloc {

    private int x;
    private int y;
    private String name;

    private boolean etat;

    public Bloc(int x, int y, String name, boolean etat) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.etat = etat;
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
