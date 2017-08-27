package com.xinyuli.flappy;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.xinyuli.flappy.screens.GameScreen;
import com.xinyuli.flappy.utils.AndroidActionResolver;
import com.xinyuli.flappy.utils.AssetLoader;

/**
 * Invoke the game.
 */
public class Flappy extends Game {

	public AndroidActionResolver androidActionResolver;

	public Flappy(AndroidActionResolver androidActionResolver) {
		this.androidActionResolver = androidActionResolver;
	}

	/**
	 * Load the assets and launch the game screen.
	 */
	@Override
	public void create () {
		Gdx.app.log("Flappy", "created");
		AssetLoader.load();
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}
}
