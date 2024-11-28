package com.bersebranggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bersebranggame.canvas.*;
import com.bersebranggame.objects.character.*;
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Chicken chickenPlayer;
    SpriteBatch spriteBatch;
    Gameplay gamePlay;
    @Override
    public void create() {
        backgroundTexture = new Texture("background.jpg");
        spriteBatch = new SpriteBatch();
        gamePlay = new Gameplay();
        chickenPlayer = new Chicken();
    }

    @Override
    public void resize(int width, int height) {
        gamePlay.viewPort.update(width, height, true); // true centers the camera
    }
    @Override
    public void render() {
        // organize code into three methods
        input();
        logic();
        draw();
    }
    
    private void input() {
    gamePlay.delta = 15;
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
    
    }
    
    private void draw() {
        ScreenUtils.clear(Color.BROWN);
        gamePlay.viewPort.apply();
        spriteBatch.setProjectionMatrix(gamePlay.viewPort.getCamera().combined);
        spriteBatch.begin();
        float worldWidth = gamePlay.viewPort.getWorldWidth();
        float worldHeight = gamePlay.viewPort.getWorldHeight();
        spriteBatch.draw(backgroundTexture,0,0, worldWidth, worldHeight);
        chickenPlayer.sprite.draw(spriteBatch);
        spriteBatch.end();
    }
}