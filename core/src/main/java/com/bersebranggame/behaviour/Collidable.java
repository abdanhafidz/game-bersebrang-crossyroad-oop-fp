package com.bersebranggame.behaviour;

import com.badlogic.gdx.graphics.g2d.Sprite;

public interface Collidable {
    boolean checkCollision(Sprite otherSprite);
}
