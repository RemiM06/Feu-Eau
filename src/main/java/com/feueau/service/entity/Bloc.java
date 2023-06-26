package com.feueau.service.entity;

import javafx.scene.paint.Color;

public class Bloc {

    private String name;
    private boolean etat;

    private String imagePath;

    public Bloc(String name, boolean etat) {
        this.name = name;
        this.etat = etat;
        if (name == "bloc") {
            this.imagePath = getClass().getResource("/img/Blocs-blocs.png").toExternalForm();
        }
        if (name == "vide") {
            this.imagePath = getClass().getResource("/img/Blocs-fonds.png").toExternalForm();
        }
        if (name == "eau") {
            this.imagePath = getClass().getResource("/img/eau.png").toExternalForm();
        }
        if (name == "feu") {
            this.imagePath = getClass().getResource("/img/lave.png").toExternalForm();
        }
        if (name == "porteFinFeu") {
            this.imagePath = getClass().getResource("/img/RedClosedDoor.png").toExternalForm();
        }
        if (name == "porteFinEau") {
            this.imagePath = getClass().getResource("/img/BlueClosedDoor.png").toExternalForm();
        }
    }

    public void changementEtat() {
        this.etat = !this.etat;
    }

    public boolean isEtat() {
        return etat;
    }

    public String getImagePath() {
        return imagePath;
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
