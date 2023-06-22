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
        joueurImageView.setLayoutX((joueur.getX() + 1) * (scene.getWidth() / level.getNombreCol()));
        joueurImageView.setLayoutY((joueur.getY() + 1) * (scene.getHeight() / level.getNombreRow()));
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
                HashSet<KeyCode> tab = new HashSet<KeyCode>();

        AnimationTimer aT = new AnimationTimer() {
            @Override
            public void handle(long l) {
                for (KeyCode k : tab) {
                    if (tab.contains(KeyCode.LEFT)) {
                        deplacementGaucheJoueur(joueur1);
                    }
                    if (tab.contains(KeyCode.RIGHT)) {
                        deplacementDroitJoueur(joueur1);
                    }
                    if (tab.contains(KeyCode.UP)) {
                        deplacementHautJoueur(joueur1);
                    }
                    if (tab.contains(KeyCode.DOWN)) {
                        deplacementBasJoueur(joueur1);
                    }

                    if (tab.contains(KeyCode.Q)) {
                        deplacementGaucheJoueur(joueur2);
                    }
                    if (tab.contains(KeyCode.D)) {
                        deplacementDroitJoueur(joueur2);
                    }
                    if (tab.contains(KeyCode.Z)) {
                        deplacementHautJoueur(joueur2);
                    }
                    if (tab.contains(KeyCode.S)) {
                        deplacementBasJoueur(joueur2);
                    }
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
        if (!this.grille[y][x - 1].isEtat()) {
            joueur.gauche();
            if (joueur.getType() == "feu") {
                joueur1ImageView.setLayoutX((x - 1) * (scene.getWidth() / level.getNombreCol()));
                joueur1ImageView.setImage(new Image(joueur.getPathImgGauche()));
            }
            if (joueur.getType() == "eau") {
                joueur2ImageView.setLayoutX((x - 1) * (scene.getWidth() / level.getNombreCol()));
                joueur2ImageView.setImage(new Image(joueur.getPathImgGauche()));
            }
            System.out.println("deplacement");
        } else {
            System.out.println("l'emplacement n'est pas null");
        }
    }

    public void deplacementDroitJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        if (!this.grille[y][x + 1].isEtat()) {
            joueur.droite();
            if (joueur.getType() == "feu") {
                joueur1ImageView.setLayoutX((x + 1) * (scene.getWidth() / level.getNombreCol()));
                joueur1ImageView.setImage(new Image(joueur.getPathImgDroit()));
            }
            if (joueur.getType() == "eau") {
                joueur2ImageView.setLayoutX((x + 1) * (scene.getWidth() / level.getNombreCol()));
                joueur2ImageView.setImage(new Image(joueur.getPathImgDroit()));
            }
            System.out.println("deplacement");
        } else {
            System.out.println("l'emplacement n'est pas null");
        }
    }
    public void deplacementHautJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        if (!this.grille[y - 1][x].isEtat()) {
            joueur.haut();
            if (joueur.getType() == "feu") {
                joueur1ImageView.setLayoutY((y - 1) * (scene.getHeight() / level.getNombreRow()));
            }
            if (joueur.getType() == "eau") {
                joueur2ImageView.setLayoutY((y - 1) * (scene.getHeight() / level.getNombreRow()));
            }
            System.out.println("deplacement");
        } else {
            System.out.println("l'emplacement n'est pas null");
        }
    }
    public void deplacementBasJoueur(Joueur joueur) {
        int x = joueur.getX();
        int y = joueur.getY();
        if (!this.grille[y + 1][x].isEtat()) {
            joueur.bas();
            if (joueur.getType() == "feu") {
                joueur1ImageView.setLayoutY((y + 1) * (scene.getHeight() / level.getNombreRow()));
            }
            if (joueur.getType() == "eau") {
                joueur2ImageView.setLayoutY((y + 1) * (scene.getHeight() / level.getNombreRow()));
            }
            System.out.println("deplacement");
        } else {
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