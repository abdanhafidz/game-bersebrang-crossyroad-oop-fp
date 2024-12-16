package com.bersebranggame.objects.character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bersebranggame.behaviour.Moveable;
import com.bersebranggame.manager.Gameplay;
import com.bersebranggame.objects.AbstractEntity;

public class Character extends AbstractEntity implements Moveable {
    private  Texture texture;
    private float width, height;
    private float positionX, positionY, speed;
    private String image;

    // Constructor with all parameters
    public Character(float width, float height, float positionX, float positionY, float speed, String image) {
        super(positionX, positionY, width, height);
        this.speed = speed;
        this.image = image;
        this.texture = new Texture(image);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(width,height);
    }

    // Constructor with optional position (position defaults to 0, 0)
    public Character(float width, float height, float speed, String image) {
        this(width, height, 0, 0, speed, image);
    }

    // Constructor with optional width, height, and position (position defaults to 0, 0, width/height default to 50)
    public Character(float speed, String image) {
        this(50, 50, 0, 0, speed, image); // Default width = 50, height = 50
    }


    @Override
    public void update() {
    }

    @Override
    public boolean checkCollision(Sprite otherSprite) {
        return false;
    }

    @Override
    public void dispose() {

    }

    public void moveRight() {
        Gameplay.delta = Gdx.graphics.getDeltaTime();
        if(this.sprite.getX() < Gameplay.viewPort.getWorldWidth() - 1){
            this.sprite.translateX(this.speed * Gameplay.delta);
        }
    }

    public void moveLeft() {
        Gameplay.delta = Gdx.graphics.getDeltaTime();
        if(this.sprite.getX() >= 0){
            this.sprite.translateX(-this.speed * Gameplay.delta);
        }
    }

    public void moveUp() {
        Gameplay.delta = Gdx.graphics.getDeltaTime();
        if(this.sprite.getY() < Gameplay.viewPort.getWorldHeight() - 1){
            this.sprite.translateY(this.speed * Gameplay.delta);
        }
    }

    public void moveDown() {
        Gameplay.delta = Gdx.graphics.getDeltaTime();
        if(this.sprite.getY() >= 0 ){
            this.sprite.translateY(-this.speed * Gameplay.delta);
        }
    }

}
