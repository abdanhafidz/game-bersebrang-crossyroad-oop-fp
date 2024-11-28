package com.bersebranggame.canvas;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
public class Gameplay{
    public FitViewport viewPort = new FitViewport(8,5);;
    public Sprite sprite;
    public Texture texture;
    public float delta = Gdx.graphics.getDeltaTime();
}
