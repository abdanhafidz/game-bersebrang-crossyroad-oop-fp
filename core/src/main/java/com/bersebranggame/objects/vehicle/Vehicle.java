package com.bersebranggame.objects.vehicle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.bersebranggame.objects.AbstractEntity;

public abstract class Vehicle extends AbstractEntity {
    protected Texture texture;
    protected float speed;
    protected boolean movingRight;

    public Vehicle(String imagePath, float x, float y, float width, float height, float speed, boolean movingRight) {

        super(x, y, width, height);

        this.texture = new Texture(imagePath);
        this.sprite = new Sprite(texture);
        this.sprite.setPosition(x, y);
        this.sprite.setSize(width, height);
        this.speed = speed;
        this.movingRight = movingRight;
    }



}