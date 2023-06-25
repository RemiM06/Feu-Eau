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
import javafx.stage.Stage;

import java.math.BigDecimal;
import java.util.HashSet;

public class Partie {

    private Scene scene;
    private Group root;
    private Level level;
    private String etatPartie;
    private GridPane gridPane;
    private Bloc[][] grille;
    private Joueur joueur1;
    private ImageView joueur1ImageView;
    private Joueur joueur2;
    private ImageView joueur2ImageView;

    public Partie(Stage stageMain, Level level) {

        this.root = new Group();
        this.scene = new Scene(root, 700, 400);
        stageMain.setScene(scene);
        stageMain.setFullScreen(true);

        this.level = level;
        this.initPartie();
    }

    public ImageView generationImageJoueur(Joueur joueur) {

        ImageView joueurImageView = new ImageView(new Image(joueur.getPathImgDroit()));
        joueurImageView.setFitWidth(scene.getWidth() / level.getNombreCol());
        joueurImageView.setFitHeight(scene.getHeight() / level.getNombreRow());
        joueurImageView.setTranslateX((joueur.getX().intValue()) * (scene.getWidth() / level.getNombreCol()));
        joueurImageView.setTranslateY((joueur.getY().intValue()) * (scene.getHeight() / level.getNombreRow()));
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
        this.grille = level.getGrille();
        this.gridPane = generationGridPane();

        this.etatPartie = "enCours";

        this.joueur1 = new Joueur(this.level.getyJoueur1(), this.level.getxJoueur1(), "feu");
        this.joueur2 = new Joueur(this.level.getyJoueur2(), this.level.getxJoueur2(), "eau");
        this.joueur1ImageView = generationImageJoueur(joueur1);
        this.joueur2ImageView = generationImageJoueur(joueur2);

        this.root.getChildren().removeAll();
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

        //Partie du programme qui tourne en boucle
        AnimationTimer aT = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (etatPartie == "perdu")
                {
                    stop();
                    initPartie();
                }
                //Verifie si le joueur est en saut ou en chut libre (même façon de descendre)
                if (joueur1.isJumping() || checkBlocY(joueur1, "bas") || (joueur1.getY().doubleValue()/joueur1.getY().intValue() != 1)) {
                    //Vitesse de la chute
                    joueur1.setyVelocity(joueur1.getyVelocity().add(new BigDecimal("0.6")));

                    //Verifie si il y a un bloc au dessus et que le joueur monte
                    if (!(checkBlocY(joueur1, "haut")) && (joueur1.getyVelocity().doubleValue() < 0)) {
                        //Met sa vitesse de mouvement vertical à 0 pour arreter de le faire monter
                        joueur1.setyVelocity(new BigDecimal("0.0"));
                    }

                    //Change la position du joueur et de son image
                    joueur1ImageView.setTranslateY(joueur1ImageView.getTranslateY() + joueur1.getyVelocity().doubleValue());
                    joueur1.setY(joueur1.getY().add(joueur1.getyVelocity().divide(new BigDecimal("60.0"))));

                    //Verifie si il y a un bloc en dessous et que le joueur descend


                    if (!(checkBlocY(joueur1, "bas")) && (joueur1.getyVelocity().doubleValue() >= 0)) {
                        //Verifie si le joueur est exactement au niveau du sol, sinon il met a sol la valeur du sol
                        int sol = (joueur1.getY().intValue());
                        if (sol/joueur1.getY().doubleValue() != 1)
                        {
                            sol += 1;
                        }
                        //Change la position du joueur et de son image pour qu'elle soit au niveau du sol
                        joueur1ImageView.setTranslateY((sol) * 60);
                        joueur1.setY(new BigDecimal(sol));
                        //Change le boolean de saut du joueur pour qu'il puisse a nouveau sauter et qu'il arrete de descendre
                        joueur1.setJumping(false);
                    }
                }

                //Verifie que le joueur a un mouvement horizontal
                if (joueur1.getxVelocity().doubleValue() != 0)
                {
                    String direction;
                    //Si le joueur se déplace vers la droite, change la valeur de direction et change l'image du joueur
                    if (joueur1.getxVelocity().doubleValue() > 0) {
                        direction = "droite";
                        joueur1ImageView.setImage(new Image(joueur1.getPathImgDroit()));
                    }
                    //Sinon c'est qu'il se déplace vers la gauche, change la valeur de direction et l'image du joueur
                    else {
                        direction = "gauche";
                        joueur1ImageView.setImage(new Image(joueur1.getPathImgGauche()));
                    }
                    //Verifie si le joueur n'a pas de bloc devant lui
                    if (checkBlocX(joueur1, direction)) {
                        //Change la position de l'image et du joueur
                        joueur1ImageView.setTranslateX(joueur1ImageView.getTranslateX() + joueur1.getxVelocity().doubleValue());
                        joueur1.setX(joueur1.getxVelocity().divide(new BigDecimal("60.0")));

                    }
                }
                if ((grille[joueur1.getY().intValue()][joueur1.getX().intValue()].getName() == "porteFinFeu") && (grille[joueur2.getY().intValue()][joueur2.getX().intValue()].getName() == "porteFinEau"))
                {
                    //Victoire
                    System.out.println("Victoire");
                }
                int x1 = joueur1.getX().intValue();
                int x2 = joueur1.getX().intValue();
                if (joueur1.getX().doubleValue()/x2 != 1) {
                    x2 += 1;
                }
                if ((grille[joueur1.getY().intValue()+1][x1].getName() == "eau") || (grille[joueur1.getY().intValue()+1][x2].getName() == "eau"))
                {
                    joueur1ImageView.setTranslateY((joueur1.getY().intValue()) * 60);
                    etatPartie = "perdu";
                }
            }
        };
        aT.start();

        //Actions des touches lorsqu'elles sont enfoncées
        this.scene.setOnKeyPressed(e -> {
            //Si la flèche de droite est enfoncée
            if (e.getCode() == KeyCode.RIGHT) {
                //Met la vitesse horizontal à 6.0
                joueur1.setxVelocity(new BigDecimal("6.0"));
            }
            //Si la flèche de gauche est enfoncée
            if (e.getCode() == KeyCode.LEFT) {
                //Met la vitesse horizontal à -6.0
                joueur1.setxVelocity(new BigDecimal("-6.0"));
            }
            //Si la flèche du haut est enfoncée, que le joueur n'est pas déjà entrain de sauter et qu'il est sur un sol
            if (e.getCode() == KeyCode.UP && !joueur1.isJumping() && !checkBlocY(joueur1, "bas")) {
                //Met sa variable de saut à vrai pour savoir qu'il est entrain de sauter et met sa vitesse vertical à -12.0
                joueur1.setJumping(true);
            }
            if (e.getCode() == KeyCode.R) {
                aT.stop();
                this.initPartie();
            }
        });
        //Actions des touches lorsqu'elles sont relachées
        this.scene.setOnKeyReleased(e -> {
            //Si la flèche de droite est relachée
            if (e.getCode() == KeyCode.RIGHT) {
                //Met la vitesse horizontal à 0.0
                joueur1.setxVelocity(new BigDecimal("0.0"));
            }
            //Si la flèche de gauche est relachée
            if (e.getCode() == KeyCode.LEFT) {
                //Met la vitesse horizontal à 0.0
                joueur1.setxVelocity(new BigDecimal("0.0"));
            }
            //Si la flèche du haut est relachée et que le joueur n'est pas entrain de sauter
            if (e.getCode() == KeyCode.UP && !joueur1.isJumping()) {
                //Met sa variable de saut à faux pour savoir qu'il n'est pas entrain de sauter et met sa vitesse vertical à 0.0
                joueur1.setJumping(false);
            }
        });
        System.out.println("initPartie");
    }

    //Verification de la presence d'un bloc solide au dessus ou en dessus en fonction du parametre direction
    public boolean checkBlocY(Joueur joueur, String direction) {
        //Valeur de X en int
        int x1 = joueur.getX().intValue();
        //Valeur de X en int au cas ou le personnage soit sur 2 cases
        int x2 = joueur.getX().intValue();
        //Si le personnage est sur 2 cases alors x2 vaut +1 pour verifier les 2 cases
        if (x2/joueur.getX().doubleValue() != 1)
        {
            x2 += 1;
        }
        //Si on veut verifier les blocs au dessus du joueur
        if (direction == "haut") {
            //On enleve 0.19 à notre y soit 1 mouvements pour avoir une marge d'erreur
            Double newY = joueur.getY().subtract(new BigDecimal("0.19")).doubleValue();
            //On ne prend que l'entier de ce newY
            int y = newY.intValue();
            //Si un des deux bloc est solide alors on renvoi faux, il y a un bloc nous empechant de passer
            if (grille[y][x1].isEtat() || grille[y][x2].isEtat()) {
                return false;
            }
        }
        //Si on veut verifier les blocs en dessous du joueur
        if (direction == "bas") {
            Double newY = joueur.getY().add(new BigDecimal("0.38")).doubleValue();
            //On ne prend que l'entier de ce newY
            int y = newY.intValue();
            //Si un des deux bloc est solide alors on renvoi faux, il y a un bloc nous empechant de passer
            if (grille[y+1][x1].isEtat() || grille[y+1][x2].isEtat()) {
                return false;
            }
        }
        //Si il n'y a pas de bloc nous empechant de passer on renvoi vrai
        return true;
    }

    public boolean checkBlocX(Joueur joueur, String direction) {
        //Valeur de Y en int
        int y1 = joueur.getY().intValue();
        //Valeur de Y en int au cas ou le personnage soit sur 2 cases
        int y2 = joueur.getY().intValue();
        //Si le personnage est sur 2 cases alors y2 vaut +1 pour verifier les 2 cases
        if (y2/joueur.getY().doubleValue() != 1)
        {
            y2 += 1;
        }
        //Si on veut verifier les blocs à la droite du joueur
        if (direction == "droite") {
            Double newX = joueur.getX().doubleValue();
            //On ne prend que l'entier de ce newY
            int x = newX.intValue();
            //Si un des deux bloc est solide alors on renvoi faux, il y a un bloc nous empechant de passer
            if (grille[y1][x+1].isEtat() || grille[y2][x+1].isEtat()) {
                return false;
            }
        }
        //Si on veut verifier les blocs à la gauche du joueur
        if (direction == "gauche") {
            //On enleve 0.1 à notre x soit la position si aucun bloc ne gene
            Double newX = joueur.getX().subtract(new BigDecimal("0.1")).doubleValue();
            //On ne prend que l'entier de ce newY
            int x = newX.intValue();
            //Si un des deux bloc est solide alors on renvoi faux, il y a un bloc nous empechant de passer
            if (grille[y1][x].isEtat() || grille[y2][x].isEtat()) {
                return false;
            }
        }
        //Si il n'y a pas de bloc nous empechant de passer on renvoi vrai
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