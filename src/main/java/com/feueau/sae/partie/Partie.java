package com.feueau.sae.partie;

import com.feueau.sae.joueur.Joueur;
import com.feueau.sae.level.Level;
import com.feueau.service.entity.Bloc;
import javafx.animation.AnimationTimer;
import javafx.beans.property.SimpleIntegerProperty;
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

    private GridPane gridPane;
    private Bloc[][] grille;
    private Joueur joueur1;
    private ImageView joueur1ImageView;
    private Joueur joueur2;
    private ImageView joueur2ImageView;

    public Partie(Scene scene, Group root, Level level) {
        this.scene = scene;
        this.root = root;
        this.level = level;
        this.grille = level.getGrille();
        this.gridPane = generationGridPane();

        this.joueur1 = new Joueur(this.level.getyJoueur1(), this.level.getxJoueur1(), "feu");
        this.joueur2 = new Joueur(this.level.getyJoueur2(), this.level.getxJoueur2(), "eau");
        this.joueur1ImageView = generationImageJoueur(joueur1);
        this.joueur2ImageView = generationImageJoueur(joueur2);

        this.root.getChildren().addAll(this.gridPane, this.joueur1ImageView, this.joueur2ImageView);

        this.scene.widthProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.root.getChildren().removeAll();
            this.gridPane = generationGridPane();
            this.joueur1ImageView = generationImageJoueur(joueur1);
            this.joueur2ImageView = generationImageJoueur(joueur2);
            this.root.getChildren().addAll(this.gridPane, this.joueur1ImageView, this.joueur2ImageView);
        }));

        this.scene.heightProperty().addListener(((observableValue, oldValue, newValue) -> {
            this.root.getChildren().removeAll();
            this.gridPane = generationGridPane();
            this.joueur1ImageView = generationImageJoueur(joueur1);
            this.joueur2ImageView = generationImageJoueur(joueur2);
            this.root.getChildren().addAll(this.gridPane, this.joueur1ImageView, this.joueur2ImageView);
        }));
        this.initPartie();
    }

    public ImageView generationImageJoueur(Joueur joueur) {

        ImageView joueurImageView = new ImageView(new Image(joueur.getPathImgDroit()));
        joueurImageView.setFitWidth(scene.getWidth() / level.getNombreCol());
        joueurImageView.setFitHeight(scene.getHeight() / level.getNombreRow());
        joueurImageView.setTranslateX((joueur.getX()) * (scene.getWidth() / level.getNombreCol()));
        joueurImageView.setTranslateY((joueur.getY()) * (scene.getHeight() / level.getNombreRow()));
        return joueurImageView;
    }

    public GridPane generationGridPane() {
        GridPane gridPane = new GridPane();
        for (var i=0;i<level.getNombreRow();i++) {
            for (var j=0;j<level.getNombreCol();j++) {
                ImageView imageBloc = new ImageView(new Image(this.grille[i][j].getImagePath()));
                imageBloc.setFitWidth(scene.getWidth() / level.getNombreCol());
                imageBloc.setFitHeight(scene.getHeight() / level.getNombreRow());
                gridPane.add(imageBloc, j, i);
            }
        }
        return gridPane;
    }

    public void initPartie() {

        this.scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                joueur1.setxVelocity(6);
            }
            if (e.getCode() == KeyCode.LEFT) {
                joueur1.setxVelocity(-6);
            }
            if (e.getCode() == KeyCode.UP && !joueur1.isJumping()) {
                joueur1.setJumping(true);
            }
        });

        this.scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.RIGHT) {
                joueur1.setxVelocity(0.0);
            }
            if (e.getCode() == KeyCode.LEFT) {
                joueur1.setxVelocity(0.0);
            }
            if (e.getCode() == KeyCode.UP && !joueur1.isJumping()) {
                joueur1.setJumping(false);
            }
        });
        new AnimationTimer() {
            @Override
            public void handle(long l) {
                // verifie si le joueur est en saut ou en chut libre (même façon de descendre)
                if (joueur1.isJumping() || checkBlocY(joueur1, "bas")) {
                    //Vitesse de la chute
                    System.out.println("------------------");
                    System.out.println((joueur1.getyVelocity()*10.0 + 6.0)/10.0);
                    joueur1.setyVelocity((joueur1.getyVelocity()*10.0 + 6.0)/10.0);
                    //ne fait plus monter le joueur si il y a un obstacle au dessus
                    if (!(checkBlocY(joueur1, "haut")) && (joueur1.getyVelocity() < 0)) {
                        joueur1.setJumping(false);
                    }
                    if (!checkBlocY(joueur1, "bas") && (joueur1.getyVelocity() > 0)) {
                        joueur1.setJumping(false);
                    }
                    // deplace le joueur
                    joueur1ImageView.setTranslateY(joueur1ImageView.getTranslateY() + joueur1.getyVelocity());
                    joueur1.setY(((joueur1.getyVelocity())*10.0 / 6.0)/10.0);
                }
                if (joueur1.getxVelocity() != 0)
                {
                    String direction;
                    if (joueur1.getxVelocity() > 0) {
                        direction = "droite";
                    }
                    else {
                        direction = "gauche";
                    }
                    if (checkBlocX(joueur1, direction)) {
                        joueur1ImageView.setTranslateX(joueur1ImageView.getTranslateX() + joueur1.getxVelocity());
                        joueur1.setX(joueur1.getxVelocity() / 6.0);
                    }
                }
            }
        }.start();
        System.out.println("initPartie");
    }

    public boolean checkBlocY(Joueur joueur, String direction) {
        //Valeur de X en int
        int x1 = joueur.getX().intValue();
        //Valeur de X en int au cas ou le personnage soit sur 2 cases
        int x2 = joueur.getX().intValue();
        //Si le personnage est sur 2 cases alors x2 vaut +1 pour verifier les 2 cases
        if (x2/joueur.getX() != 1)
        {
            x2 += 1;
        }
        int y = joueur.getY().intValue();
        if (direction == "haut") {
            if (grille[y][x1].isEtat() || grille[y][x2].isEtat()) {
                return false;
            }
        }
        if (direction == "bas") {
            if (grille[y+1][x1].isEtat() || grille[y+1][x2].isEtat()) {
                return false;
            }
        }
        return true;
    }

    public boolean checkBlocX(Joueur joueur, String direction) {
        //Valeur de Y en int
        int y1 = joueur.getY().intValue();
        //Valeur de Y en int au cas ou le personnage soit sur 2 cases
        int y2 = joueur.getY().intValue();
        //Si le personnage est sur 2 cases alors y2 vaut +1 pour verifier les 2 cases
        if (y2/joueur.getY() != 1)
        {
            y2 += 1;
        }


        if (direction == "droite") {
            Double newX = joueur.getX()+0.1;
            int x = newX.intValue();
            if (grille[y1][x+1].isEtat() || grille[y2][x+1].isEtat()) {
                return false;
            }
        }
        if (direction == "gauche") {
            Double newX = joueur.getX()-0.1;
            int x = newX.intValue();

            if (grille[y1][x].isEtat() || grille[y2][x].isEtat()) {
                return false;
            }
        }
        return true;
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