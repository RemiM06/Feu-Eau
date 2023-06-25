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

        this.joueur1 = new Joueur(this.level.       getyJoueur1(), this.level.getxJoueur1(), "feu");
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
                joueur1.setxVelocity(5.5);
            }
            if (e.getCode() == KeyCode.LEFT) {
                joueur1.setxVelocity(-5.5);
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
                if (joueur1.isJumping()) {
                    System.out.println(joueur1.getyVelocity());
                    joueur1.setyVelocity(joueur1.getyVelocity() + 0.6);
                    joueur1ImageView.setTranslateY(joueur1ImageView.getTranslateY() + joueur1.getyVelocity());

                    if (joueur1ImageView.getTranslateY() >= (scene.getHeight()/level.getNombreRow())*16) {
                        joueur1ImageView.setTranslateY((scene.getHeight()/level.getNombreRow()*16));
                        joueur1.setJumping(false);
                    }
                }
                joueur1ImageView.setTranslateX(joueur1ImageView.getTranslateX() + joueur1.getxVelocity());
            }
        }.start();
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