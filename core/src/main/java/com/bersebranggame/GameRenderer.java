package com.bersebranggame;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.bersebranggame.manager.GamePlayManager;
import com.bersebranggame.manager.Gameplay;
import com.bersebranggame.manager.ScoreManager;
import com.bersebranggame.objects.character.Character;
import com.bersebranggame.objects.character.Chicken;
import com.bersebranggame.objects.obstacle.Log;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.objects.vehicle.Vehicle;

public class GameRenderer {

    private SpriteBatch spriteBatch;
    private Texture backgroundTexture;
    private ScoreManager scoreManager;
    private GamePlayManager gamePlayManager;
    private Character character;

    public GameRenderer(SpriteBatch spriteBatch, Texture backgroundTexture,
                        ScoreManager scoreManager, GamePlayManager gamePlayManager,
                        Character character) {

        this.spriteBatch = spriteBatch;
        this.backgroundTexture = backgroundTexture;
        this.scoreManager = scoreManager;
        this.gamePlayManager = gamePlayManager;
        this.character= character;
    }

    public void render() {
        // Clear the screen
        ScreenUtils.clear(Color.BROWN);

        // Apply viewport
        Gameplay.viewPort.apply();
        spriteBatch.setProjectionMatrix(Gameplay.viewPort.getCamera().combined);

        // Begin sprite batch
        spriteBatch.begin();

        // Get world dimensions
        float worldWidth = Gameplay.viewPort.getWorldWidth();
        float worldHeight = Gameplay.viewPort.getWorldHeight();

        // Draw background
        spriteBatch.draw(backgroundTexture, 0, 0, worldWidth, worldHeight);

        // Draw obstacles
        for (Obstacle obs : gamePlayManager.getObs()) {
            obs.getSprite().draw(spriteBatch);
        }

        // Draw vehicles
        for (Vehicle car : gamePlayManager.getCars()) {
            car.getSprite().draw(spriteBatch);
        }

        // Draw logs
        for (Log log : gamePlayManager.getLogs()) {
            log.getSprite().draw(spriteBatch);
        }

        // Draw chicken player
        character.getSprite().draw(spriteBatch);

        // Draw score
        scoreManager.drawScore(0.45f, 9.8f);

        // End sprite batch
        spriteBatch.end();
    }

    public void dispose() {
        // Note: Do not dispose of textures or sprite batch here if they are
        // managed by the main game class
        backgroundTexture.dispose();
    }
}
