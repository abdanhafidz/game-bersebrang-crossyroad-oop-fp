package com.bersebranggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bersebranggame.manager.GamePlayManager;
import com.bersebranggame.manager.Gameplay;
import com.bersebranggame.objects.character.Chicken;
import com.bersebranggame.objects.obstacle.Log;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.objects.enviroment.River;
import com.bersebranggame.Input.InputHandler;
import com.bersebranggame.objects.vehicle.Vehicle;

public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Chicken chickenPlayer;
    SpriteBatch spriteBatch;
    private boolean inRiver = true;
    private boolean gameOver = false;
    private boolean onLog = false;
    private InputHandler inputHandler;
    private GamePlayManager gamePlayManager;
    private int score;
    private String yourScoreName;
    BitmapFont yourBitmapFontName;
    private float lastPosition;


    public void create() {
        backgroundTexture = new Texture("background.jpg");
        spriteBatch = new SpriteBatch();

        // Atur ukuran viewport
        Gameplay.viewPort.setWorldSize(10, 10);
        Gameplay.viewPort.getCamera().position.set(5, 5, 0); // Pusatkan kamera di tengah viewport
        Gameplay.viewPort.getCamera().update();

        gamePlayManager = new GamePlayManager();
        gamePlayManager.spawnEntities();

        chickenPlayer = new Chicken();
        inputHandler = new InputHandler(chickenPlayer);

        lastPosition = 0;
        score = 0;


        yourScoreName = "score : 0";

        yourBitmapFontName = new BitmapFont(Gdx.files.internal("font2.fnt"));
        yourBitmapFontName.getData().setScale(0.05f);
    }

    @Override
    public void resize(int width, int height) {
        Gameplay.viewPort.update(width, height, true); // true centers the camera
    }

    @Override
    public void render() {
        if (!gameOver) {
            inputHandler.handelInput();
            logic();
        }
        draw();
    }


    private void logic() {

        spriteBatch = Gameplay.spriteBatch;
        gamePlayManager.updateCars();
        gamePlayManager.updateLog();

        for (Vehicle car : gamePlayManager.getCars()) {
            if (car.checkCollision(chickenPlayer.getSprite())) {
                gameOver = true;
            }
        }

        if (chickenPlayer.getSprite().getY() >= Gameplay.viewPort.getWorldHeight() - 1) {
            chickenPlayer.getSprite().setY(0);

            gamePlayManager.dispose();
            gamePlayManager.setNewObs();
            gamePlayManager.spawnEntities();
            lastPosition = 0;
        }

        onLog = false; //
        for (Log log : gamePlayManager.getLogs()) {

            if (Intersector.overlaps(chickenPlayer.getSprite().getBoundingRectangle(), log.getSprite().getBoundingRectangle())) {

                onLog = true;

                if (Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
                    inputHandler.handelInput();
                }
                else {
                    chickenPlayer.getSprite().setX(log.getSprite().getX());
                    chickenPlayer.getSprite().setY(log.getSprite().getY());

                }
                break;
            }
        }

        if (!onLog) {
            for (Obstacle obs : gamePlayManager.getObs()) {
                if (obs instanceof River && chickenPlayer.getSprite().getBoundingRectangle().overlaps(obs.getSprite().getBoundingRectangle())) {
                    System.out.println("Masuk sungai");
                    gameOver = true;
                }
            }
        }

        System.out.println("Postion ayam sekarang:" + chickenPlayer.getSprite().getY());

        if ((int)chickenPlayer.getSprite().getY() > lastPosition){

            yourScoreName = "score :  " + score;
            score++;
            lastPosition = (int)chickenPlayer.getSprite().getY();
            System.out.println("score bertamabah");
        }
    }

    @Override
    public void resume() {
        super.resume();
        if (spriteBatch == null) {
            Gameplay.spriteBatch = new SpriteBatch();
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BROWN);

        Gameplay.viewPort.apply(); // Terapkan viewport
        spriteBatch.setProjectionMatrix(Gameplay.viewPort.getCamera().combined);

        spriteBatch.begin();
        float worldWidth = Gameplay.viewPort.getWorldWidth();
        float worldHeight = Gameplay.viewPort.getWorldHeight();

        // Gambar latar belakang
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);

        // Gambar objek dalam permainan
        for (Obstacle r_Obstacle : gamePlayManager.getObs()) {
            r_Obstacle.getSprite().draw(spriteBatch);
        }
        for (Vehicle car : gamePlayManager.getCars()) {
            car.getSprite().draw(spriteBatch);
        }
        for (Log log : gamePlayManager.getLogs()) {
            log.getSprite().draw(spriteBatch);
        }
        chickenPlayer.getSprite().draw(spriteBatch);


        yourBitmapFontName.setColor(Color.WHITE);
        yourBitmapFontName.draw(spriteBatch, yourScoreName, 0.5f, 9.5f); ; // Posisi skor di bagian atas
        spriteBatch.end();
    }

}
