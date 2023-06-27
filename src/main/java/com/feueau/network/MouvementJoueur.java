package com.feueau.network;

public class MouvementJoueur {

    private String playerId;
    private double x;
    private double y;

    public MouvementJoueur(){

    }

    public MouvementJoueur(String playerId, double x, double y) {
        this.playerId = playerId;
        this.x = x;
        this.y = y;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
