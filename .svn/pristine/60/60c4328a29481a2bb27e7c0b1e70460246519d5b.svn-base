package com.xinyuli.flappy.tests;

import com.xinyuli.flappy.gameobjects.Bird;


/**
 * Created by xinyuli on 02/05/2017.
 *
 * Tests for class Bird.
 */
public class BirdTest {

    private static Bird bird;

    public static void main(String[] args) throws Exception {
        bird = new Bird(50, 200);

        testJump();
        testUpdate();
        testAlive();
        testRestart();
    }

    private static void testJump() throws Exception {

        // initial positions and alive
        if (bird.getX() != 50 || bird.getY() != 200 || !bird.isAlive()) {
            throw new Exception();
        }

        // after jump the velocity should be increased
        float origY = bird.getVelocity().y;
        bird.jump();
        if (bird.getY() != 200) {
            throw new Exception();
        }
        if (bird.getVelocity().y <= origY) {
            throw new Exception();
        }
        if (bird.isFalling()) {
            throw new Exception();
        }
    }

    private static void testUpdate() throws Exception {
        // after update it should fall
        int counter = 0;
        while (counter < 1000) {
            bird.update(counter ++);
        }
        if (!bird.isFalling()) {
            throw new Exception();
        }
        // should be alive because no collision handling in bird class.
        if (!bird.isAlive()) {
            throw new Exception();
        }
    }

    private static void testAlive() throws Exception {
        bird.die();
        if (bird.isAlive()) {
            throw new Exception();
        }

        // cannot jump after death
        float origY = bird.getVelocity().y;
        bird.jump();
        if (bird.getVelocity().y != origY) {
            throw new Exception();
        }
    }

    private static void testRestart() throws Exception {
        bird.onRestart(50, 200);
        if (bird.getX() != 50 || bird.getY() != 200 || !bird.isAlive()) {
            throw new Exception();
        }
    }
}
