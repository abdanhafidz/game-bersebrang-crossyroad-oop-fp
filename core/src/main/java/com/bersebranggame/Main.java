package com.bersebranggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bersebranggame.canvas.Gameplay;
import com.bersebranggame.objects.character.Chicken;
/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Chicken chickenPlayer;
    SpriteBatch spriteBatch;
    @Override
    public void create() {
        backgroundTexture = new Texture("background.jpg");
        spriteBatch = new SpriteBatch();

        chickenPlayer = new Chicken();
    }

    @Override
    public void resize(int width, int height) {
        Gameplay.viewPort.update(width, height, true); // true centers the camera
    }
    @Override
    public void render() {
        // organize code into three methods
        input();
        logic();
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
    
    }
    
    private void draw() {
        ScreenUtils.clear(Color.BROWN);
        Gameplay.viewPort.apply();
        spriteBatch.setProjectionMatrix(Gameplay.viewPort.getCamera().combined);
        spriteBatch.begin();
        float worldWidth = Gameplay.viewPort.getWorldWidth();
        float worldHeight = Gameplay.viewPort.getWorldHeight();
        System.out.println(worldHeight);
        spriteBatch.draw(backgroundTexture,0,0, worldWidth, worldHeight);
        chickenPlayer.sprite.draw(spriteBatch);
        spriteBatch.end();
    }
}