package com.xinyuli.flappy.gameworlds;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.xinyuli.flappy.Flappy;
import com.xinyuli.flappy.gameobjects.Bird;
import com.xinyuli.flappy.gameobjects.ScrollHandler;
import com.xinyuli.flappy.utils.AssetLoader;
import com.xinyuli.flappy.utils.Constants;

/**
 * Created by xinyuli on 09/04/2017.
 *
 * Our cute city.
 */

public class GameWorld {

    private enum GameState {
        READY, RUNNING, GAMEOVER
    }

    private Flappy game;

    private Bird bird;
    private Rectangle ground;
    private static final int GROUND_Y_OFFSET = -50;
    private ScrollHandler scrollHandler;
    private int midY;

    private int score= 0;
    private boolean newHighScore = false;

    private int prevVolume = 0;

    private GameState currState;

    private boolean showSoundValue = false;
    private boolean touchMode = false;



    public GameWorld(int midY, Flappy game) {
        this.game = game;

        currState = GameState.READY;
        this.midY = midY;
        bird = new Bird(50, midY + 5);
        ground = new Rectangle(0, GROUND_Y_OFFSET, Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT);
        scrollHandler = new ScrollHandler(this, touchMode);
    }

    /**
     * Update based on current game state.
     * @param dt Delta time.
     */
    public void update(float dt) {
        switch (currState) {
            case RUNNING:
                AssetLoader.font.getData().setScale(0.5f);
                AssetLoader.shadow.getData().setScale(0.5f);
                updateRunning(dt);
                break;
            default:
                break;
        }
    }

    /**
     * Start the game with running state.
     */
    public void start() {
        currState = GameState.RUNNING;
        scrollHandler.onRestart(touchMode);
    }

    /**
     * Restart the game to ready state.
     */
    public void restart() {
        score = 0;
        bird.onRestart(50, midY + 5);
        scrollHandler.onRestart(touchMode);
        currState = GameState.READY;
        newHighScore = false;
    }

    /**
     * Show google play service high score.
     */
    public void showHighScore() {
        game.androidActionResolver.showScore();
    }

    /**
     * Invert the showSound status.
     */
    public void changeShowSound() {
        showSoundValue = !showSoundValue;
    }

    /**
     * Update bird and scroll handler. Handle collision cases. Handle sound input.
     * @param dt Delta time.
     */
    private void updateRunning(float dt) {
        bird.update(dt);
        scrollHandler.update(dt);
        if (!touchMode) {
            int volume = game.androidActionResolver.getVolume();
            if (volume > 6000) {
                if (volume - prevVolume > 5000) {
                    bird.jump();
                }
            }
            prevVolume = volume;
        }

        if (scrollHandler.collides(bird) && bird.isAlive()) {
            scrollHandler.stop();
            bird.die();
            AssetLoader.hit.play(0.5f);
        }
        if (Intersector.overlaps(bird.getBoundingCircle(), ground)) {
            scrollHandler.stop();
            bird.die();
            bird.decelerate();
            AssetLoader.die.play(0.5f);
            currState = GameState.GAMEOVER;
            if (score > AssetLoader.getHighScore()) {
                AssetLoader.setHighScore(score);
                newHighScore = true;
            }
            game.androidActionResolver.submitScore(AssetLoader.getHighScore());
        }
    }

    public Bird getBird() {
        return bird;
    }

    ScrollHandler getScrollHandler() {
        return scrollHandler;
    }

    int getScore() {
        return score;
    }

    public void addScore(int incre) {
        score += incre;
    }

    boolean isNewHighScore() {
        return newHighScore;
    }

    boolean showSoundValue() {
        return showSoundValue;
    }

    public boolean isTouchMode() {
        return touchMode;
    }

    public void changeTouchMode() {
        touchMode = !touchMode;
    }

    public boolean isReady() {
        return currState == GameState.READY;
    }

    public boolean isRunning() {
        return currState == GameState.RUNNING;
    }

    public boolean isGameOver() {
        return currState == GameState.GAMEOVER;
    }

    public void showToast(String text) {
        game.androidActionResolver.showToast(text);
    }

    int getVolume() {
        return game.androidActionResolver.getVolume();
    }
}
