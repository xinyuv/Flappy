package com.xinyuli.flappy.gameobjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by xinyuli on 11/04/2017.
 *
 * Scrollable objects in the game.
 */

public class Scrollable {

    Vector2 position;
    private Vector2 velocity;
    int width, height;
    private boolean isScrolledLeft;

    public Scrollable(float x, float y, int width, int height, float scrollSpeed) {
        position = new Vector2(x, y);
        velocity = new Vector2(scrollSpeed, 0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;
    }

    /**
     * Moving towards the left of the screen.
     */
    public void update(float dt) {
        position.add(velocity.cpy().scl(dt));
        if (position.x + width < 0) {           // out of screen now
            isScrolledLeft = true;
        }
    }

    /**
     * Reset the object to the new x location.
     */
    public void reset(float newX) {
        position.x = newX;
        isScrolledLeft = false;
    }

    /**
     * Stop moving towards left.
     */
    public void stop() {
        velocity.set(0, 0);
    }

    /**
     * Reset x and scrollSpeed.
     */
    public void onRestart(float x, float scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }

    public void onRestart(float x, float scrollSpeed, boolean touchMode) {
        position.x = x;
        velocity.x = scrollSpeed;
    }

    public float getX() {
        return position.x;
    }

    public float getTailX() {
        return position.x + width;
    }

    public float getY() {
        return position.y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }
}
