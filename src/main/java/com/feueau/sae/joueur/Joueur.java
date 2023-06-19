package com.feueau.sae.joueur;

import javafx.scene.input.KeyEvent;

import java.security.Key;

public class Joueur {

    private int y=1;
    private int x=1;

    public Joueur() {
    }
    public Joueur(int y, int x) {
        this.y=y;
        this.x=x;
    }
    public int getY() {
        return y;
    }
    public int getX() {
        return x;
    }

    public void gauche() {
        this.x -= 1;
    }

    public void droite() {
        this.x += 1;
    }

    public void haut() {
        this.y += 1;
    }

    public void bas() {
        this.y -= 1;
    }
}
