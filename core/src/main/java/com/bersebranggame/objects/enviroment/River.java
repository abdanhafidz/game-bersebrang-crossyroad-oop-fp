package com.bersebranggame.objects.enviroment;
import com.bersebranggame.manager.Gameplay;
import com.bersebranggame.objects.obstacle.Obstacle;

public class River extends Obstacle {
    public River(){
        super(
            Gameplay.width,
            1,
            "river.png"

        );
    }
}
