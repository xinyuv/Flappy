package com.xinyuli.flappy.gameobjects;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.xinyuli.flappy.utils.AssetLoader;
import com.xinyuli.flappy.utils.Constants;

/**
 * Created by xinyuli on 10/04/2017.
 *
 * Our cute bird.
 */

public class Bird {

    private static final int GRAVITY = -700;

    private boolean isAlive = true;

    private Vector2 position, velocity, acceleration;
    private float rotation;
    private int width, height;

    private Circle boundingCircle;

    /**
     * Initialize bird with x and y positions.
     */
    public Bird(float x, float y) {
        this.width = Constants.BIRD_WIDTH;
        this.height = Constants.BIRD_HEIGHT;
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        acceleration = new Vector2(0, GRAVITY);
        boundingCircle = new Circle();
    }

    /**
     * Velocity adds GRAVITY every dt.
     * Position adds velocity every dt.
     * Updates position and bounding circle as well.
     * Handles rotation.
     */
    public void update(float dt) {
        velocity.add(acceleration.cpy().scl(dt));
        if (velocity.y < -500) {
            velocity.y = -500;
        } else if (velocity.y > 500) {
            velocity.y = 500;
        }

        position.add(velocity.cpy().scl(dt));
        if (position.y < 0) {
            position.y = 0;
        }
        boundingCircle.set(position.x + Constants.BIRD_WIDTH/2,
                position.y + Constants.BIRD_HEIGHT/2, 12.f);

        // rotate up
        if (velocity.y > 0) {
            rotation += 600 * dt;
            if (rotation > 25) {
                rotation = 25;
            }
        }

        // rotate down
        if (isFalling() || !isAlive) {
            rotation -= 200 * dt;
            if (rotation < -90) {
                rotation = -90;
            }
        }
    }

    /**
     * Restart: reset x, y positions, rotation, acceleration and isAlive.
     */
    public void onRestart(float x, float y) {
        rotation = 0;
        position.set(x, y);
        velocity.set(0, 0);
        acceleration.set(0, GRAVITY);
        isAlive = true;
    }

    /**
     * @return If the bird is falling.
     */
    public boolean isFalling() {
        return velocity.y < -130;
    }

    /**
     * @return If the bird should flap its wing.
     */
    public boolean shouldFlap() {
        return isAlive && velocity.y > -120;
    }

    /**
     * Bird jumps.
     */
    public void jump() {
        if (isAlive) {
            velocity.y = 250;
//            AssetLoader.wing.play(0.5f);
        }
    }

    public void die() {
        isAlive = false;
        velocity.y = 0;
    }

    /**
     * Clean the acceleration.
     */
    public void decelerate() {
        acceleration.y = 0;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getRotation() {
        return rotation;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Circle getBoundingCircle() {
        return boundingCircle;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
}
