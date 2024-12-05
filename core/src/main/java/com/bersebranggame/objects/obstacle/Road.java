package com.bersebranggame.objects.obstacle;
import com.bersebranggame.canvas.Gameplay;
public class Road extends Obstacle {
    public Road(){
        super(
            Gameplay.width,
            3,
            "road.png"
        );
    }
}
