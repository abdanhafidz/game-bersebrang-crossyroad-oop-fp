package com.bersebranggame.canvas;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.objects.obstacle.River;
import com.bersebranggame.objects.obstacle.Road;
import jdk.internal.org.jline.terminal.TerminalBuilder;

public class Gameplay{

    public static int width = 8;
    public static int height = 10;
    public static FitViewport viewPort = new FitViewport(width,height);
    public static SpriteBatch spriteBatch = new SpriteBatch();
    public Sprite sprite;
    public Texture texture;
    public static float delta = Gdx.graphics.getDeltaTime();
    public static Array<Obstacle> obstacles;
    public static Obstacle obsChoosen;


    public static Array<Obstacle> createObstacles(){
        Array<Obstacle> obstacles = new Array<>();
        Array<Obstacle> randomizer = new Array<>();
        randomizer.add(new River());
        randomizer.add(new Road());


        Random rand = new Random();
        int choosenIdx = rand.nextInt(1, 3);

        if (choosenIdx == 1) {
            obsChoosen = new River();
        } else if (choosenIdx == 2) {
            obsChoosen = new Road();
        }

        obsChoosen.sprite.setY(1);  // Set the initial position
        obsChoosen.setPositionY(1);
        obstacles.add(obsChoosen);

        while (obsChoosen.sprite.getY() < height - (obsChoosen.sprite.getHeight() + 2)) {
            float prevObsPosition = obsChoosen.sprite.getY();

            choosenIdx = rand.nextInt(1, 3);
            if (choosenIdx == 1) {
                obsChoosen = new Road();
            } else if (choosenIdx == 2) {
                obsChoosen = new River();
            }

            // Set a random Y offset based on the previous obstacle
            int randDist = rand.nextInt(1, 3);

            // Update the position based on previous Y
            obsChoosen.sprite.setY(prevObsPosition + randDist);

            obsChoosen.setPositionY(obsChoosen.sprite.getY());
            obstacles.add(obsChoosen);

        }
        return obstacles;
}

}
