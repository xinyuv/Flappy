package com.xinyuli.flappy.utils;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xinyuli.flappy.gameobjects.Bird;
import com.xinyuli.flappy.gameworlds.GameWorld;

/**
 * Created by xinyuli on 10/04/2017.
 *
 * Input Handler.
 */

public class InputHandler implements InputProcessor {

    private GameWorld world;
    private Viewport viewport;
    private Bird bird;

    private int changeModeCounter;

    public InputHandler(GameWorld world, Viewport viewport) {
        this.world = world;
        this.viewport = viewport;
        this.bird = world.getBird();
    }

    /**
     * Send out different response based on where is touched and which state is the game in.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector2 newXY = new Vector2(screenX, screenY);
        viewport.unproject(newXY);                      // change the coordinates to game scale.

        boolean playBtnTouched = newXY.x >= 120-82.6 && newXY.x <= 120-82.6+72.8
                && newXY.y >= 200-98 && newXY.y <= 200-98+40.6;
        boolean leaderBoardBtnTouched = newXY.x >= 120+10 && newXY.x <= 120+10+72.8
                && newXY.y >= 200-98 && newXY.y <= 200-98+40.6;
        boolean birdNameTouched = newXY.x >= 50 && newXY.x <= 50+32
                && newXY.y >= 205 && newXY.y <= 205+24;
        boolean settingTouched = newXY.x >= 120-23.25 && newXY.x <= 120-23.25+46.5
                && newXY.y >= 200-98-27-10 && newXY.y <= 200-98-10;

        System.out.println(newXY.x + " " + newXY.y);

        if (world.isReady()) {
            if (playBtnTouched) {
                world.start();
                bird.jump();
            } else if (leaderBoardBtnTouched) {
                world.showHighScore();
            } else if (birdNameTouched) {
                world.showToast("Ouch!");
            } else if (settingTouched) {
                world.changeShowSound();
                changeModeCounter ++;
                if (changeModeCounter % 10 == 0) {
                    world.changeTouchMode();
                }
            }
        } else if (world.isRunning()) {
            if (world.isTouchMode()) {
                bird.jump();
            }
        } else if (world.isGameOver()) {
            if (playBtnTouched) {
                world.restart();
            } else if (leaderBoardBtnTouched) {
                world.showHighScore();
            } else if (settingTouched) {
                world.changeShowSound();
                changeModeCounter ++;
                if (changeModeCounter % 10 == 0) {
                    world.changeTouchMode();
                }
            }
        }
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
