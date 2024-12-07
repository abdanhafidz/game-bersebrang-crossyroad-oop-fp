package com.bersebranggame.canvas;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.bersebranggame.objects.obstacle.*;

import java.util.Random;


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
        disposePreviousLevel();
        this.i = this.i + i;
        this.j = this.j + j;
        generateCar();
    }

    public void setObstacles(Array<Obstacle> obstacles) {
        this.obstacles = obstacles;
    }

    public void generateCar() {
        Random rand = new Random();
        for (Obstacle obstacle : obstacles) {
            float randomFloat = i + rand.nextFloat() * (j - i);
            System.out.println(randomFloat);
            boolean randomDirection = rand.nextBoolean();

            if (obstacle instanceof Road){
                System.out.println("buat mobil cik");
            if (randomDirection){
                cars.add(new Car("car.png", Gameplay.width, obstacle.getPositionY(),
                    1.0f, 0.5f, randomFloat, randomDirection));
            }else {
                cars.add(new Car("car2.png", obstacle.getPositionX(), obstacle.getPositionY(),
                    1.0f, 0.5f, randomFloat, false));
            }}
            else if (obstacle instanceof River){
                logs.add(new Log("log.png", obstacle.getPositionX(), obstacle.getPositionY(),
                    2f, 1f, randomFloat, false));
            }
        }
    }

    public void updateCars() {
        for (Car car : cars) {
            car.update();
        }
    }

    public void updateLog() {
        for (Log log : logs) {
            log.update();
        }
    }

    public float getI() {
        return i;
    }

    public Array<Obstacle> getObstacles() {
        return obstacles;
    }

    public Array<Car> getCars() {
        return cars;
    }

    public Array<Log> getLogs() {
        return logs;
    }

    public Texture getBackgroundTexture() {
        return backgroundTexture;
    }

    private void disposePreviousLevel() {
        // Dispose of previous background
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

    public void dispose() {
        disposePreviousLevel();
    }
}
