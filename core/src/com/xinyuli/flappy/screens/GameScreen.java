package com.xinyuli.flappy.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.xinyuli.flappy.Flappy;
import com.xinyuli.flappy.gameworlds.GameRenderer;
import com.xinyuli.flappy.gameworlds.GameWorld;
import com.xinyuli.flappy.utils.Constants;
import com.xinyuli.flappy.utils.InputHandler;

/**
 * Created by xinyuli on 09/04/2017.
 * <p>
 * Main game screen.
 */

public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;
    private Viewport viewport;
    private float runTime;

    public GameScreen(Flappy game) {
        Gdx.app.log("GameScreen", "Attached");

        world = new GameWorld(Constants.GAME_HEIGHT / 4, game);
        renderer = new GameRenderer(world);
        viewport = new FitViewport(Constants.GAME_WIDTH/2, Constants.GAME_HEIGHT/2, renderer.getCam());
        Gdx.input.setInputProcessor(new InputHandler(world, viewport));
    }

    @Override
    public void render(float dt) {
        runTime += dt;
        world.update(dt);
        renderer.render(runTime);
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen", "show called");
    }

    /**
     * Update viewport.
     */
    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
        Gdx.app.log("GameScreen", "resizing");
    }

    @Override
    public void pause() {
        Gdx.app.log("GameScreen", "pause called");
    }

    @Override
    public void resume() {
        Gdx.app.log("GameScreen", "resume called");
    }

    @Override
    public void hide() {
        Gdx.app.log("GameScreen", "hide called");
    }

    @Override
    public void dispose() {
        Gdx.app.log("GameScreen", "dispose called");
    }
}
