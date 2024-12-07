package com.bersebranggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bersebranggame.canvas.Gameplay;
import com.bersebranggame.canvas.LevelManager;
import com.bersebranggame.objects.character.Chicken;
import com.bersebranggame.objects.obstacle.Car;
import com.bersebranggame.objects.obstacle.Obstacle;

public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Chicken chickenPlayer;
    SpriteBatch spriteBatch;
    LevelManager levelManager;
    private boolean gameOver = false;


    @Override
    public void create() {
        backgroundTexture = new Texture("background.jpg");
        spriteBatch = Gameplay.spriteBatch;
        Gameplay.obstacles = Gameplay.createObstacles();

        chickenPlayer = new Chicken();
        levelManager = new LevelManager(Gameplay.obstacles, 0, 2);
    }

    @Override
    public void resize(int width, int height) {
        Gameplay.viewPort.update(width, height, true); // true centers the camera
    }

    @Override
    public void render() {
        if (!gameOver) {
            input();
            logic();
        }
        draw();
    }

    private void input() {
    Gameplay.delta = 15;
    if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
        chickenPlayer.moveLeft();
    }
    if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
        chickenPlayer.moveRight();
    }
    if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        chickenPlayer.moveUp();
    }

    if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        chickenPlayer.moveDown();
    }
    }

    private void logic() {
        if (spriteBatch == null) {
            Gameplay.spriteBatch = new SpriteBatch();
        }
        spriteBatch = Gameplay.spriteBatch;
        levelManager.updateCars();


        for (Car car : levelManager.getCars()) {

            if (car.checkCollision(chickenPlayer.getSprite())) {
                System.out.println("tabrakan");
                gameOver = true;
            }
        }

        if (chickenPlayer.getSprite().getY() >= Gameplay.viewPort.getWorldHeight() - 1) {

            chickenPlayer.getSprite().setY(0);


            Gameplay.obstacles = Gameplay.createObstacles();

            levelManager.setObstacles(Gameplay.obstacles);

            levelManager.setupLevel(levelManager.getI() + 0.01f , levelManager.getI() + 0.01f);
        }



//        System.out.println(Gameplay.obstacles.size);
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
        Gameplay.viewPort.apply();
        spriteBatch.setProjectionMatrix(Gameplay.viewPort.getCamera().combined);
        spriteBatch.begin();
        float worldWidth = Gameplay.viewPort.getWorldWidth();
        float worldHeight = Gameplay.viewPort.getWorldHeight();
        spriteBatch.draw(backgroundTexture,0,0, worldWidth, worldHeight);

        for(Obstacle r_Obstacle : Gameplay.obstacles){
            r_Obstacle.sprite.draw(spriteBatch);
        }

        for (Car car : levelManager.getCars()) {
            car.getSprite().draw(spriteBatch);
        }

        chickenPlayer.sprite.draw(spriteBatch);
        spriteBatch.end();
    }
    @Override
    public void dispose() {
    }
}
