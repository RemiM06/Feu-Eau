package com.feueau.sae.partie;

import com.feueau.sae.joueur.Joueur;
import com.feueau.sae.level.Level;
import com.feueau.service.entity.Bloc;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.HashSet;

public class Partie {

    private Scene scene;
    private Group root;
    private Level level;

    private Bloc[][] grille;
    private Joueur joueur1;
    private Joueur joueur2;

    public Partie(Scene scene, Group root, Level level) {
        this.scene = scene;
        this.root = root;
        this.level = level;
        this.grille = level.getGrille();
        GridPane gridPane = new GridPane();

        this.root.getChildren().addAll(gridPane);
        this.initPartie();
    }

    public void initPartie() {
        this.joueur1 = new Joueur(this.level.getxJoueur1(), this.level.getyJoueur1(), "feu");
        this.joueur2 = new Joueur(this.level.getxJoueur2(), this.level.getyJoueur2(), "eau");

        HashSet<KeyCode> tab = new HashSet<KeyCode>();

        AnimationTimer aT = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (KeyCode k : tab) {
                    //System.out.println(k.getName());
                    if (tab.contains(KeyCode.LEFT)) {
                        deplacementGaucheJoueur(joueur1);
                    }
                    if (tab.contains(KeyCode.RIGHT)) {
                        deplacementDroitJoueur(joueur1);
                    }
                    if (tab.contains(KeyCode.UP)) {
                        deplacementHautJoueur(joueur1);
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

    public void deplacementGaucheJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        if (!this.grille[y][x-1].isEtat()) {
            joueur.gauche();
        }
        else {
            System.out.println("l'emplacement n'est pas null");
        }
    }
    public void deplacementDroitJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        if (!this.grille[y][x+1].isEtat()) {
            joueur.droite();
        }
        else {
            System.out.println("l'emplacement n'est pas null");
        }
    }
    public void deplacementHautJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        if (!this.grille[y+1][x].isEtat()) {
            joueur.haut();
        }
        else {
            System.out.println("l'emplacement n'est pas null");
        }
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