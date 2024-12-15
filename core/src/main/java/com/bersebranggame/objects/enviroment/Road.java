package com.bersebranggame.objects.enviroment;
import com.bersebranggame.canvas.Gameplay;
import com.bersebranggame.objects.obstacle.Obstacle;

public class Road extends Obstacle {
    public Road(){
        super(
            Gameplay.width,
            1,
            "road.png"
        );
    }
}
