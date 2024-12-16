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
import com.bersebranggame.manager.ScoreManager;
import com.bersebranggame.objects.character.Chicken;
import com.bersebranggame.objects.obstacle.Log;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.objects.enviroment.River;
import com.bersebranggame.Input.InputHandler;
import com.bersebranggame.objects.obstacle.Rock;
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
    private ScoreManager scoreManager; // Menambahkan ScoreManager
    private float lastPositionX;
    private float lastPositionY;

    public void create() {
        backgroundTexture = new Texture("background.jpg");
        spriteBatch = new SpriteBatch();

        Gameplay.viewPort.setWorldSize(10, 10);
        Gameplay.viewPort.getCamera().position.set(5, 5, 0); // Pusatkan kamera di tengah viewport
        Gameplay.viewPort.getCamera().update();

        gamePlayManager = new GamePlayManager();
        gamePlayManager.spawnEntities();

        chickenPlayer = new Chicken();
        inputHandler = new InputHandler(chickenPlayer);

        lastPositionX = 0;
        lastPositionY = 0;

        BitmapFont font = new BitmapFont(Gdx.files.internal("font3.fnt"));
        font.getData().setScale(0.042f);
        scoreManager = new ScoreManager(font);

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

        System.out.println("Position X:" + lastPositionX + " Y:" + lastPositionY);
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
            lastPositionY = 0;
        }

        onLog = false;
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

                if (obs instanceof Rock && chickenPlayer.getSprite().getBoundingRectangle().overlaps(obs.getSprite().getBoundingRectangle())) {
                    chickenPlayer.getSprite().setX(chickenPlayer.getPrevX());
                    chickenPlayer.getSprite().setY(chickenPlayer.getPrevY());
                }
            }
        }

        if ((int)chickenPlayer.getSprite().getY() > lastPositionY){
            System.out.println("Lebih tinggi" + " Score:" + scoreManager.getScore());
            scoreManager.incrementScore();
            lastPositionY = (int)chickenPlayer.getSprite().getY();
            System.out.println("score bertambah");
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

        Gameplay.viewPort.apply();
        spriteBatch.setProjectionMatrix(Gameplay.viewPort.getCamera().combined);

        spriteBatch.begin();
        float worldWidth = Gameplay.viewPort.getWorldWidth();
        float worldHeight = Gameplay.viewPort.getWorldHeight();

        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);

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

        scoreManager.drawScore(0.45f, 9.8f);
        spriteBatch.end();
    }
}
