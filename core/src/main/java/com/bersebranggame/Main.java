package com.bersebranggame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    Texture backgroundTexture;
    Texture chickenTexture;
    Texture dropTexture;
    Sound dropSound;
    Music music;
    SpriteBatch spriteBatch;
    FitViewport viewport;
    Sprite chickenSprite; 
    @Override
    public void create() {
        backgroundTexture = new Texture("background.jpg");
        chickenTexture = new Texture("chicken.png");
        // dropTexture = new Texture("drop.png");
        spriteBatch = new SpriteBatch();
        viewport = new FitViewport(8, 5);
        chickenSprite = new Sprite(chickenTexture); // Initialize the sprite based on the texture
        chickenSprite.setSize(1, 1);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true); // true centers the camera
    }
    @Override
    public void render() {
        // organize code into three methods
        input();
        logic();
        draw();
    }
    
    private void input() {
    float speed = 4f;
    float delta = Gdx.graphics.getDeltaTime(); // retrieve the current delta
    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
        if(chickenSprite.getX() < viewport.getWorldWidth() - 1){
            chickenSprite.translateX(speed * delta );
        }
    }if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
        if(chickenSprite.getX() >= 0){
            chickenSprite.translateX(-speed * delta );
        }
    }if(Gdx.input.isKeyPressed(Input.Keys.UP)){
        if(chickenSprite.getY() < viewport.getWorldHeight() - 1){
            chickenSprite.translateY(speed * delta );
        }
    }if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
        if(chickenSprite.getY() >= 0){
            chickenSprite.translateY(-speed * delta );
        }
    }
    }
    
    private void logic() {
    
    }
    
    private void draw() {
        ScreenUtils.clear(Color.BROWN);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        spriteBatch.draw(backgroundTexture,0,0, worldWidth, worldHeight);
        chickenSprite.draw(spriteBatch);
        spriteBatch.end();
    }
}