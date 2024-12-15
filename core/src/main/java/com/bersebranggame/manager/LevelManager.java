package com.bersebranggame.manager;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.bersebranggame.objects.obstacle.*;
import com.bersebranggame.objects.vehicle.Car;


public class LevelManager {
    private Texture backgroundTexture;
    private final Array<Car> cars = new Array<>();
    private Array<Obstacle> obstacles;
    private Array<Log>  logs = new Array<>();
    private float i;
    private  float j;

    public LevelManager(Array<Obstacle> obs, float i, float j) {
        this.obstacles = obs ;
        this.i = i;
        this.j = j;

        setupLevel(i, j);
    }

    public void setupLevel(float i, float j) {
//        disposePreviousLevel();
        this.i = this.i + i;
        this.j = this.j + j;
    }

    public void setObstacles(Array<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public float getI() {
        return i;
    }


    private void disposePreviousLevel() {
        if (backgroundTexture != null) {
            backgroundTexture.dispose();
        }

        // Dispose of previous cars
        for (Car car : cars) {
            car.dispose();
        }

        for (Log log : logs) {
            log.dispose();
        }

        logs.clear();


        cars.clear();
    }
}
