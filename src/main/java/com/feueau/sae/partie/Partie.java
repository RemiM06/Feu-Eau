package com.feueau.sae.partie;

import com.feueau.sae.joueur.Joueur;
import com.feueau.sae.level.Level;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

import java.util.HashSet;

public class Partie {

    Scene scene;

    Level level;

    Joueur joueur1;
    Joueur joueur2;

    public Partie(Scene scene, Level level) {
        this.scene = scene;
        this.level = level;

        this.initPartie();
    }

    public void initPartie() {
        this.joueur1 = new Joueur(this.level.getxJoueur1(), this.level.getyJoueur1());
        this.joueur2 = new Joueur(this.level.getxJoueur2(), this.level.getyJoueur2());

        HashSet<KeyCode> tab = new HashSet<KeyCode>();

        AnimationTimer aT = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (KeyCode k : tab) {
                    //System.out.println(k.getName());
                    if (tab.contains(KeyCode.LEFT)) {
                        joueur1.gauche();
                    }
                    if (tab.contains(KeyCode.RIGHT)) {
                        joueur1.droite();
                    }
                    if (tab.contains(KeyCode.UP)) {
                        joueur1.haut();
                    }
                    if (tab.contains(KeyCode.DOWN)) {
                        joueur1.bas();
                    }
                    System.out.println(joueur1);
                }
            }
        };

        this.scene.setOnKeyPressed(e -> {
            boolean wasEmpty = tab.isEmpty();
            if(tab.add(e.getCode()) && wasEmpty)
                aT.start();
        });

        this.scene.setOnKeyReleased(e -> {
            if(tab.remove(e.getCode()) && tab.isEmpty())
                aT.stop();
        });
        System.out.println("initPartie");
    }

    public Scene getScene() {
        return scene;
    }

    public Level getLevel() {
        return level;
    }

    public Joueur getJoueur1() {
        return joueur1;
    }

    public Joueur getJoueur2() {
        return joueur2;
    }
}