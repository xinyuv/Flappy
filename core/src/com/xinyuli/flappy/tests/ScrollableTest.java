package com.xinyuli.flappy.tests;

import com.xinyuli.flappy.gameobjects.City;
import com.xinyuli.flappy.gameobjects.Ground;
import com.xinyuli.flappy.gameobjects.Scrollable;
import com.xinyuli.flappy.gameobjects.Tube;

/**
 * Created by xinyuli on 02/05/2017.
 *
 * Tests for class Scrollable.
 */
public class ScrollableTest {

    public static void main(String[] args) throws Exception {
        testParentClass();
        testCity();
        testGround();
        testTube();
    }

    private static void testParentClass() throws Exception {

        // test orig positions
        Scrollable scrollable = new Scrollable(50, 100, 240, 400, -100);
        if (scrollable.isScrolledLeft() || scrollable.getX() != 50|| scrollable.getY() != 100
                || scrollable.getTailX() != 50 + 240) {
            throw new Exception();
        }
        if (scrollable.getWidth() != 240 || scrollable.getHeight() != 400) {
            throw new Exception();
        }

        // should move left after update, but not to out of screen
        scrollable.update(1);
        if (scrollable.getX() >= 50) {
            throw new Exception();
        }
        if (scrollable.isScrolledLeft()) {
            throw new Exception();
        }

        // should be scrolled left after a lot of updates.
        int count = 0;
        while (count < 200) {
            scrollable.update(count ++);
        }
        if (!scrollable.isScrolledLeft()) {
            throw new Exception();
        }

        // test reset
        scrollable.reset(300);
        if (scrollable.getX() != 300) {
            throw new Exception();
        }

        // should not be moving after stop
        scrollable.stop();
        count = 0;
        while (count < 200) {
            scrollable.update(count ++);
        }
        if (scrollable.getX() != 300) {
            throw new Exception();
        }

        // move again after restart
        scrollable.onRestart(50, -200);
        count = 0;
        while (count < 200) {
            scrollable.update(count ++);
        }
        if (scrollable.getX() >= 50) {
            throw new Exception();
        }
    }

    private static void testCity() throws Exception {
        // only test for constructor because it is the only thing in child class.
        City city = new City(50, 100, 240, 400, -100);
        if (city.isScrolledLeft() || city.getX() != 50 || city.getY() != 100
                || city.getTailX() != 50 + 240) {
            throw new Exception();
        }
        if (city.getWidth() != 240 || city.getHeight() != 400) {
            throw new Exception();
        }
    }

    private static void testGround() throws Exception {
        // only test for constructor because it is the only thing in child class.
        Ground ground = new Ground(50, 100, 240, 400, -100);
        if (ground.isScrolledLeft() || ground.getX() != 50 || ground.getY() != 100
                || ground.getTailX() != 50 + 240) {
            throw new Exception();
        }
        if (ground.getWidth() != 240 || ground.getHeight() != 400) {
            throw new Exception();
        }
    }

    private static void testTube() throws Exception {
        Tube tube = new Tube(50, 100, 240, 400, -100, true);
        // no assertion for y because y is randomly selected.
        if (tube.isScrolledLeft() || tube.getX() != 50 || tube.getTailX() != 50 + 240) {
            throw new Exception();
        }
        if (tube.getWidth() != 240 || tube.getHeight() != 400) {
            throw new Exception();
        }

        // test for random range
        int counter = 0;
        while (counter < 100) {
            if (!(tube.getY() >= 210 && tube.getY() <= 210 + 130)) {
                throw new Exception();
            }
            if (tube.getY() != tube.getBotY() + 90 + 320) {
                throw new Exception();
            }
            counter ++;
            tube.reset(50);
        }

        // change mode
        tube.onRestart(50, -100, false);
        if (tube.isScrolledLeft() || tube.getX() != 50 || tube.getTailX() != 50 + 240) {
            throw new Exception();
        }
        if (tube.getWidth() != 240 || tube.getHeight() != 400) {
            throw new Exception();
        }

        // test for if mode change selects correct tube gap
        counter = 0;
        while (counter < 100) {
            if (!(tube.getY() >= 230 && tube.getY() <= 230 + 130)) {
                throw new Exception();
            }
            if (tube.getY() != tube.getBotY() + 150 + 320) {
                throw new Exception();
            }
            counter ++;
            tube.reset(50);
        }
    }
 }
