package com.xinyuli.flappy.gameobjects;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.xinyuli.flappy.utils.Constants;

import java.util.Random;

/**
 * Created by xinyuli on 11/04/2017.
 *
 * Green tubes.
 */

public class Tube extends Scrollable {

    private Random rand;
    private static final int TUBE_GAP = 90;
    private static final int TUBE_GAP_EASY = 150;
    private static final int FLUCTUATION = 130;         // move up/down randomly between 0 - 130
    private static final int LOWEST_OPENING = 120;
    private static final int LOWEST_OPENING_EASY = 80;

    private Rectangle topTube, botTube;

    private boolean isScored = false;

    private int tubeGap, lowestOpening;


    public Tube(float x, float y, int width, int height, float scrollSpeed, boolean touchMode) {
        super(x, y, width, height, scrollSpeed);
        tubeGap = touchMode ? TUBE_GAP : TUBE_GAP_EASY;
        lowestOpening = touchMode ? LOWEST_OPENING : LOWEST_OPENING_EASY;
        rand = new Random();
        position.y = rand.nextInt(FLUCTUATION) + tubeGap + lowestOpening;
        topTube = new Rectangle();
        botTube = new Rectangle();
    }

    /**
     * Reset the tube to the new X position. Y position are random chosen.
     * @param newX New x position.
     */
    @Override
    public void reset(float newX) {
        super.reset(newX);
        position.y = rand.nextInt(FLUCTUATION) + tubeGap + lowestOpening;
        isScored = false;
    }

    /**
     * Update tube locations.
     * @param dt Delta time.
     */
    @Override
    public void update(float dt) {
        super.update(dt);
        topTube.set(position.x, position.y, width, height);
        botTube.set(position.x, getBotY(), width, height);
    }

    /**
     * Reset constants based on touchMode.
     */
    @Override
    public void onRestart(float x, float scrollSpeed, boolean touchMode) {
        tubeGap = touchMode ? TUBE_GAP : TUBE_GAP_EASY;
        lowestOpening = touchMode ? LOWEST_OPENING : LOWEST_OPENING_EASY;
        super.onRestart(x, scrollSpeed);
        reset(x);
    }

    /**
     * @return if bird overlaps the tubes.
     */
    boolean collides(Bird bird) {

        // check for the tubes in the screen
        return position.x >= 0 && position.x <= Constants.GAME_WIDTH
                 && (Intersector.overlaps(bird.getBoundingCircle(), topTube)
                || Intersector.overlaps(bird.getBoundingCircle(), botTube));
    }

    /**
     * @return The position of the bottom tube.
     */
    public float getBotY() {
        return position.y - tubeGap - Constants.TUBE_HEIGHT;
    }

    /**
     * @return if this tube has already been passed by the bird.
     */
    boolean isScored() {
        return isScored;
    }

    void setScored() {
        isScored = true;
    }
}
