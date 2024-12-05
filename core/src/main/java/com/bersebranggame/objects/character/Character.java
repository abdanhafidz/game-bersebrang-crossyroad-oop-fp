package com.bersebranggame.objects.character;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.bersebranggame.behaviour.Moveable;
import com.bersebranggame.behaviour.Spriteable;
import com.bersebranggame.canvas.Gameplay;
public class Character extends Gameplay implements Moveable, Spriteable {
    private int width, height;
    private float positionX, positionY, speed;
    private String image;
    // Default constructor
    public Character() {
        this.width = 0;
        this.height = 0;
        this.positionX = 0;
        this.positionY = 0;
        this.speed = 0f;
        this.image = "";
        this.texture = null;
    }

    // Constructor with all parameters
    public Character(int width, int height, float positionX, float positionY, float speed, String image) {
        this.width = width;
        this.height = height;
        this.positionX = positionX;
        this.positionY = positionY;
        this.speed = speed;
        this.image = image;
        this.texture = new Texture(image);
        this.sprite = new Sprite(texture);
        this.sprite.setSize(width,height);
    }

    // Constructor with optional position (position defaults to 0, 0)
    public Character(int width, int height, float speed, String image) {
        this(width, height, 0, 0, speed, image);
    }

    // Constructor with optional width, height, and position (position defaults to 0, 0, width/height default to 50)
    public Character(float speed, String image) {
        this(50, 50, 0, 0, speed, image); // Default width = 50, height = 50
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
        if (texture != null) {
            texture.dispose(); // Dispose previous texture to free memory
        }
        this.texture = new Texture(image);
        this.sprite = new Sprite(texture);
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }
    @Override
    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }
    @Override
    public Texture getTexture() {
        return texture;
    }
    @Override
    public void setTexture(Texture texture) {
        this.texture = texture;
        this.sprite = new Sprite(texture);
    }

    // Methods for movement
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
        }else{
            // Gameplay.spriteBatch.
            // System.out.println(Gameplay.height);
        }
    }

    public void moveDown() {
        Gameplay.delta = Gdx.graphics.getDeltaTime(); 
        if(this.sprite.getY() >= 0 ){
            this.sprite.translateY(-this.speed * Gameplay.delta);
        }
    }

    public void collision() {
        // Collision logic here
    }
}
