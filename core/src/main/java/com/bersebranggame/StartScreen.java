package com.bersebranggame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class StartScreen {
    private Texture backgroundTexture;
    private BitmapFont font;
    private boolean startGame = false;

    public StartScreen() {
        backgroundTexture = new Texture("start_screen_background.png"); // Ganti dengan path background layar awal
        font = new BitmapFont(Gdx.files.internal("font3.fnt"));
        font.getData().setScale(0.05f);
        font.setColor(Color.WHITE);
    }

    public void render(SpriteBatch spriteBatch) {
        ScreenUtils.clear(Color.BLACK);

        spriteBatch.begin();
        spriteBatch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        font.draw(spriteBatch, "Press ENTER to Start", Gdx.graphics.getWidth() / 2f - 100, Gdx.graphics.getHeight() / 2f);
        spriteBatch.end();

        // Deteksi input untuk memulai game
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            startGame = true;
        }
    }

    public boolean isStartGame() {
        return startGame;
    }

    public void dispose() {
        backgroundTexture.dispose();
        font.dispose();
    }
}
