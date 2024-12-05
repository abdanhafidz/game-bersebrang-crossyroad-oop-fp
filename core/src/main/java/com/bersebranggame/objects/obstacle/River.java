package com.bersebranggame.objects.obstacle;
import com.bersebranggame.canvas.Gameplay;
public class River extends Obstacle  {
    public River(){
        super(
            Gameplay.width,
            3,
            "river.png"
        );
    }
}
