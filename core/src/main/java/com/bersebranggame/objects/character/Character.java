package com.bersebranggame.objects.character;
import com.bersebranggame.behaviour.MoveAble;
import com.badlogic.gdx.graphics.g2d.Sprite;
public class Character implement MoveAble{
    private int width, height;
    private float positionX, positionY, speed;
    private String image;
    private Sprite sprite;
    private Texture texture;
    public void Character(){
    }
}
