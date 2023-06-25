package com.feueau.service.entity;

import java.util.HashMap;
import java.util.Map;

public class Bloc {
    private final String imagePath;
    private int y;
    private int x;
    private String name;
    private boolean etat;

    private static Map<String, String> imagePathCache = new HashMap<>();

    public Bloc(int y, int x, String name, boolean etat) {
        this.y = y;
        this.x = x;
        this.name = name;
        this.etat = etat;
        this.imagePath = getImagePath(name);
    }

    private String getImagePath(String name) {
        if (!imagePathCache.containsKey(name)) {
            String imagePath = null;
            switch (name) {
                case "bloc":
                    imagePath = getClass().getResource("/img/Blocs-blocs.png").toExternalForm();
                    break;
                case "vide":
                    imagePath = getClass().getResource("/img/Blocs-fonds.png").toExternalForm();
                    break;
                case "eau":
                    imagePath = getClass().getResource("/img/eau.png").toExternalForm();
                    break;
                case "lave":
                    imagePath = getClass().getResource("/img/lave.png").toExternalForm();
                    break;
                case "porteFinFeu":
                    imagePath = getClass().getResource("/img/RedClosedDoor.png").toExternalForm();
                    break;
                case "porteFinEau":
                    imagePath = getClass().getResource("/img/BlueClosedDoor.png").toExternalForm();
                    break;
            }
            if (imagePath != null) {
                imagePathCache.put(name, imagePath);
            }
        }
        return imagePathCache.get(name);
    }

    public void changementEtat() {
        this.etat = !this.etat;
    }

    public boolean isEtat() {
        return etat;
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
