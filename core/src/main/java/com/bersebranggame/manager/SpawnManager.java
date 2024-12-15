package com.bersebranggame.manager;


import com.bersebranggame.objects.enviroment.River;
import com.bersebranggame.objects.enviroment.Road;
import com.bersebranggame.objects.obstacle.Log;
import com.bersebranggame.objects.vehicle.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SpawnManager {
    private  List<Car> cars;
    private  List<Log> logs;
    private  Random random;

    public SpawnManager() {
        this.cars = new ArrayList<>();
        this.logs = new ArrayList<>();
        this.random = new Random();
    }

    public void spawnEntities(List<Object> obstacles) {
        for (Object obstacle : obstacles) {
            if (obstacle instanceof Road) {
                spawnCar((Road) obstacle);
            } else if (obstacle instanceof River) {
                spawnLog((River) obstacle);
            }
        }
    }

    private void spawnCar(Road road) {
        boolean randomDirection = random.nextBoolean();
        float randomSpeed = generateRandomSpeed(1.0f, 3.0f);

        Car car = new Car(
            randomDirection ? "car.png" : "car2.png",
            randomDirection ? Gameplay.width : 0,
            road.getPositionY(),
            1.0f,
            0.5f,
            randomSpeed,
            randomDirection
        );
        cars.add(car);
    }

    private void spawnLog(River river) {
        float randomSpeed = generateRandomSpeed(1.5f, 2.5f);

        Log log = new Log(
            "log.png",
            random.nextFloat() * Gameplay.width,
            river.getPositionY(),
            2.0f,
            1.0f,
            randomSpeed,
            false
        );
        logs.add(log);
    }

    private float generateRandomSpeed(float min, float max) {
        return min + random.nextFloat() * (max - min);
    }

    public List<Car> getCars() {
        return cars;
    }

    public List<Log> getLogs() {
        return logs;
    }
}
