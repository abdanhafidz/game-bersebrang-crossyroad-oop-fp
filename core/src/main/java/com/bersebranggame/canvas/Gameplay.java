package com.bersebranggame.canvas;
import java.util.ArrayList;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.objects.obstacle.River;
import com.bersebranggame.objects.obstacle.Road;
public class Gameplay{
    public static int width = 8, height = 6;
    public static FitViewport viewPort = new FitViewport(width,height);
    public static SpriteBatch spriteBatch = new SpriteBatch();
    public Sprite sprite;
    public Texture texture;
    public static float delta = Gdx.graphics.getDeltaTime();
    // public static Obstacle obstaclesArr;
    public static ArrayList<Obstacle> getObstacles(){
        ArrayList<Obstacle> obstacles = new ArrayList<Obstacle>();
        ArrayList<Obstacle> randomizer = new ArrayList<Obstacle>();
        randomizer.add(new River());
        randomizer.add(new Road());
        // randomizer = [ObjRiver, ObjRoad]
        int T = 3;
        int pos_obs_y = 3;
       for(int i = 1; i<= 3; i++){
            Random rand = new Random();
            int choosenIdx = rand.nextInt(1);
            Obstacle obsChoosen = randomizer.get(choosenIdx);
            obsChoosen.sprite.setPosition(0, pos_obs_y);
            obstacles.add(obsChoosen);
            pos_obs_y += 3;
        }
        return obstacles;
    }
}
