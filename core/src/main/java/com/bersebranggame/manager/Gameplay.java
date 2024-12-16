package com.bersebranggame.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class Gameplay{
    public static int width = 12;
    public static int height = 12;
    public static FitViewport viewPort = new FitViewport(width,height);
    public static SpriteBatch spriteBatch = new SpriteBatch();
    public static float delta = Gdx.graphics.getDeltaTime();

}
