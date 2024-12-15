package com.bersebranggame.objects.vehicle;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;
import com.bersebranggame.behaviour.Moveable;
import com.bersebranggame.canvas.Gameplay;

import static com.bersebranggame.canvas.Gameplay.delta;

public class Car extends Vehicle implements Moveable {
    public Car(String imagePath, float x, float y, float width, float height, float speed, boolean movingRight) {
        super(imagePath, x, y, width, height, speed, movingRight);
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        if (movingRight) {
            // Ensure sprite is facing right
            if (sprite.isFlipX()) {
                sprite.flip(true, false);
            }
            sprite.translateX(speed * delta);

            // If car goes beyond right boundary, reset to left
            if (sprite.getX() > Gameplay.viewPort.getWorldWidth()) {
                sprite.setX(-sprite.getWidth());
            }
        } else {
            // Ensure sprite is facing left
            if (!sprite.isFlipX()) {
                sprite.flip(true, false);
            }
            sprite.translateX(-speed * delta);

            // If car goes beyond left boundary, reset to right
            if (sprite.getX() < -sprite.getWidth()) {
                sprite.setX(Gameplay.viewPort.getWorldWidth());
            }
        }
    }

    @Override
    public boolean checkCollision(Sprite otherSprite) {
        return sprite.getBoundingRectangle().overlaps(otherSprite.getBoundingRectangle());
    }

    @Override
    public void dispose() {
        if (texture != null) {
            texture.dispose();
        }
    }

    @Override
    public void moveRight() {
        if (sprite.isFlipX()) {
            sprite.flip(true, false);
        }
        sprite.translateX(speed * delta);

        // If car goes beyond right boundary, reset to left
        if (sprite.getX() > Gameplay.viewPort.getWorldWidth()) {
            sprite.setX(-sprite.getWidth());
        }
    }

    @Override
    public void moveLeft() {

    }

    @Override
    public void moveUp() {

    }

    @Override
    public void moveDown() {

    }
}
