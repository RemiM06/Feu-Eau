package com.feueau.sae.partie;

import com.feueau.sae.joueur.Joueur;
import com.feueau.sae.level.Level;
import javafx.scene.Scene;

public class Partie {

    Scene scene;

    Level level;

    Joueur joueur1;
    Joueur joueur2;

    public Partie(Scene scene) {
        this.scene = scene;
    }

}