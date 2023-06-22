package com.feueau.service.entity;

public class Entite {

    private int x;
    private int y;

    private boolean traversable;
    private String visuel;

    public Entite(int x, int y, boolean traversable){
        this.x = x;
        this.y = y;
        this.traversable = traversable;

    }

    public int getX() {
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

    public boolean isTraversable() {
        return traversable;
    }

    public void setTraversable(boolean traversable) {
        this.traversable = traversable;
    }

    public String getVisuel() {
        return visuel;
    }

    public void setVisuel(String visuel) {
        this.visuel = visuel;
    }
}
