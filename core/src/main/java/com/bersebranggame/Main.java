package com.bersebranggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bersebranggame.manager.GamePlayManager;
import com.bersebranggame.manager.Gameplay;
import com.bersebranggame.manager.LevelManager;
import com.bersebranggame.manager.SpawnManager;
import com.bersebranggame.objects.character.Chicken;
import com.bersebranggame.objects.obstacle.Log;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.objects.enviroment.River;
import com.bersebranggame.Input.InputHandler;
import com.bersebranggame.objects.vehicle.Car;
import com.bersebranggame.objects.vehicle.Vehicle;

public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Chicken chickenPlayer;
    SpriteBatch spriteBatch;
    LevelManager levelManager;
    private boolean inRiver = true;
    private boolean gameOver = false;
    private boolean onLog = false;
    private InputHandler inputHandler;
    private GamePlayManager gamePlayManager;

    @Override
    public void create() {
        backgroundTexture = new Texture("background.jpg");
//        spriteBatch = Gameplay.spriteBatch;
        spriteBatch = new SpriteBatch();
//        Gameplay.obstacles = Gameplay.createObstacles();

        gamePlayManager = new GamePlayManager();

        gamePlayManager.spawnEntities();

        chickenPlayer = new Chicken();


//        levelManager = new LevelManager(Gameplay.obstacles, 0, 1);

        inputHandler = new InputHandler(chickenPlayer);
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

        if (spriteBatch == null) {
            Gameplay.spriteBatch = new SpriteBatch();
        }

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
//            Gameplay.obstacles = Gameplay.createObstacles();
//            levelManager.setObstacles(Gameplay.obstacles);


            gamePlayManager.dispose();
            gamePlayManager.setNewObs();
            gamePlayManager.spawnEntities();

//            levelManager.setupLevel(levelManager.getI() + 0.01f, levelManager.getI() + 0.01f);
        }

        onLog = false; // Reset status onLog
        for (Log log : gamePlayManager.getLogs()) {

            if (Intersector.overlaps(chickenPlayer.getSprite().getBoundingRectangle(), log.getSprite().getBoundingRectangle())) {

                onLog = true;

                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    chickenPlayer.moveUp();
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
                    chickenPlayer.moveDown();
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                    chickenPlayer.moveLeft();
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    chickenPlayer.moveRight();
                }
                else {
                    chickenPlayer.sprite.setX(log.getSprite().getX());
                    chickenPlayer.sprite.setY(log.getSprite().getY());
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
    }



    @Override
    public void resume() {
        super.resume();
        // Reload SpriteBatch and texture if needed
        if (spriteBatch == null) {
            Gameplay.spriteBatch = new SpriteBatch();
        }
    }

    private void draw() {
        ScreenUtils.clear(Color.BROWN);
//       Gameplay.viewPort.apply();
        spriteBatch.setProjectionMatrix(Gameplay.viewPort.getCamera().combined);

        spriteBatch.begin();
        float worldWidth = Gameplay.viewPort.getWorldWidth();
        float worldHeight = Gameplay.viewPort.getWorldHeight();
        spriteBatch.draw(backgroundTexture,0,0, worldWidth, worldHeight);

        for(Obstacle r_Obstacle : gamePlayManager.getObs()){
            r_Obstacle.sprite.draw(spriteBatch);
        }



        for (Vehicle car : gamePlayManager.getCars()) {
            System.out.println("gambar mobil");
            car.getSprite().draw(spriteBatch);
        }

        for (Log log : gamePlayManager.getLogs()){
            log.getSprite().draw(spriteBatch);
        }

        chickenPlayer.sprite.draw(spriteBatch);

        spriteBatch.end();
    }

}
