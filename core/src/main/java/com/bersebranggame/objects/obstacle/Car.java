package com.bersebranggame.objects.obstacle;

import com.bersebranggame.behaviour.Moveable;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
public class Car extends Sprite{
    private String direction = "left";
    public Car(){
        super(new Texture("chicken.png"));
        setSize(1,1);
    }
    public Car(float positionX, float positionY){
        super(new Texture("chicken.png"));
        setSize(1,1);
        setPosition(positionX, positionY);
    }
    public Car(float positionX, float positionY, String direction){
        this(positionX, positionY);
        this.direction = direction;
    }

    public void setDirection(String direction ){
        if(!this.direction.equals(direction)){
            this.flip(false, true);
        }
        
    }


}
