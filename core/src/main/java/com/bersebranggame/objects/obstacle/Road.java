package com.bersebranggame.objects.obstacle;
import com.bersebranggame.canvas.Gameplay;
import com.badlogic.gdx.graphics.g2d.Batch;
import java.util.ArrayList;
import java.util.Random;
import com.bersebranggame.canvas.Gameplay;
public class Road extends Obstacle {
    public Road(){
        super(
            Gameplay.width,
            1,
            "road.png"
        );
    }
    @Override
    public void draw(Batch batch){
        Random rand = new Random();
        super.draw(batch);
        int numCars;
        if(Gameplay.level <= 5){
            numCars = rand.nextInt(1, Gameplay.level + 1);
        }else{
            numCars = rand.nextInt(1, 4 + Gameplay.level % 5);
        }
        int direction = rand.nextInt(1,3);
        for(int i = 1; i<= numCars; i++){
            Car car = new Car();
            if(direction == 1){
                car.setDirection("left");
                car.setPosition(0, this.getY());
            }else{
                car.setDirection("right");
                car.setPosition(Gameplay.width, this.getY());
            }
            car.draw(batch);
        }
    }
}
