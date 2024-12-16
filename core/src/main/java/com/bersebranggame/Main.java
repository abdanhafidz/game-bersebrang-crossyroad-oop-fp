package com.bersebranggame;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.bersebranggame.manager.GamePlayManager;
import com.bersebranggame.manager.Gameplay;
import com.bersebranggame.manager.ScoreManager;
import com.bersebranggame.objects.character.Chicken;
import com.bersebranggame.objects.obstacle.Log;
import com.bersebranggame.objects.obstacle.Obstacle;
import com.bersebranggame.Input.InputHandler;
import com.bersebranggame.objects.obstacle.Rock;
import com.bersebranggame.objects.vehicle.Vehicle;
import com.bersebranggame.screen.StartScreen;

public class Main extends ApplicationAdapter {
    private enum GameState {
        START, GAMEPLAY
    }
    private GameState currentState; // State game
    private Texture backgroundTexture;
    private Texture startScreenBackground; // Latar belakang untuk layar awal
    private Chicken chickenPlayer;
    private SpriteBatch spriteBatch;
    private boolean onLog = false;
    private InputHandler inputHandler;
    private GamePlayManager gamePlayManager;
    private ScoreManager scoreManager;
    private BitmapFont font;
    private StartScreen startScreen;
    private GameRenderer gameRenderer; // New game renderer
    private GameLogic gameLogic;

    @Override
    public void create() {

        startScreen = new StartScreen();
        currentState = GameState.START;


        spriteBatch = Gameplay.spriteBatch;

        startScreenBackground = new Texture("start_screen_background.png");
        backgroundTexture = new Texture("background.jpg");

        font = new BitmapFont(Gdx.files.internal("font3.fnt"));
        font.getData().setScale(0.042f);

        gamePlayManager = new GamePlayManager();
        scoreManager = new ScoreManager(font);

        chickenPlayer = new Chicken();
        inputHandler = new InputHandler(chickenPlayer);

        gameRenderer = new GameRenderer(spriteBatch, backgroundTexture,
            scoreManager, gamePlayManager, chickenPlayer);

        gameLogic = new GameLogic(gamePlayManager, chickenPlayer, inputHandler, scoreManager);

    }

    @Override
    public void render() {
        switch (currentState) {
            case START:
                startScreen.render(spriteBatch);
                if (startScreen.isStartGame()) {
                    currentState = GameState.GAMEPLAY;
                    gamePlayManager.spawnEntities();
                }
                break;

            case GAMEPLAY:
                if (!Gameplay.gameOver) {
                    inputHandler.handelInput();
                    gameLogic.logic();
                }
                gameRenderer.render();
                break;
        }
    }

//    private void logic() {
//        gamePlayManager.updateCars();
//        gamePlayManager.updateLog();
//
//
//        // Collision detection with cars
//        for (Vehicle car : gamePlayManager.getCars()) {
//            if (car.checkCollision(chickenPlayer.getSprite())) {
//                Gameplay.gameOver = true;
//            }
//        }
//
//        if (chickenPlayer.getSprite().getY() >= Gameplay.viewPort.getWorldHeight() - 1) {
//            chickenPlayer.getSprite().setY(0);
//
//            gamePlayManager.dispose();
//            gamePlayManager.setNewObs();
//            gamePlayManager.spawnEntities();
//
//            Gameplay.lastPositionY = 0;
//        }

//        boolean onLog = false;
//        for (Log log : gamePlayManager.getLogs()) {
//            if (Intersector.overlaps(chickenPlayer.getSprite().getBoundingRectangle(), log.getSprite().getBoundingRectangle())) {
//                onLog = true;
//
//                // Check if the player is above the log
//                if (chickenPlayer.getSprite().getY() >= log.getSprite().getY() &&
//                    chickenPlayer.getSprite().getY() <= log.getSprite().getY() + log.getSprite().getHeight()) {
//
//                    // If no key is pressed, make the player move with the log
//
//                    float delta_ = - Gdx.graphics.getDeltaTime();
//                    if (log.isMovingRight()){
//                        delta_ = delta_ * - 1;
//                    }
//
//                    if (!Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)) {
//                        float logMovementX = log.getSpeed() * delta_; // Assuming the log has a movement speed
//                        chickenPlayer.getSprite().translateX(logMovementX);
//                    } else {
//                        inputHandler.handelInput();
//                    }
//
//                    if (chickenPlayer.getSprite().getX() >= Gameplay.viewPort.getWorldWidth() ||
//                        chickenPlayer.getSprite().getX() < 0) {
//                        Gameplay.gameOver = true;
//                    }
//                }
//            }
//        }
//
//        if (!onLog) {
//            for (Obstacle obs : gamePlayManager.getObs()) {
//                if (obs instanceof Log.River && chickenPlayer.getSprite().getBoundingRectangle().overlaps(obs.getSprite().getBoundingRectangle())) {
//                    System.out.println("Masuk sungai");
//                    Gameplay.gameOver = true;
//                }
//
//                if (obs instanceof Rock && chickenPlayer.getSprite().getBoundingRectangle().overlaps(obs.getSprite().getBoundingRectangle())) {
//                    chickenPlayer.getSprite().setX(chickenPlayer.getPrevX());
//                    chickenPlayer.getSprite().setY(chickenPlayer.getPrevY());
//                }
//            }
//        }
//
//        // Update score if chicken moves higher
//        if ((int) chickenPlayer.getSprite().getY() > Gameplay.lastPositionY) {
//            scoreManager.incrementScore();
//            Gameplay.lastPositionY = (int) chickenPlayer.getSprite().getY();
//        }
//    }

    @Override
    public void resize(int width, int height) {
        Gameplay.viewPort.update(width, height, true);
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        startScreenBackground.dispose();
        backgroundTexture.dispose();
        font.dispose();
        gamePlayManager.dispose();
    }
}
