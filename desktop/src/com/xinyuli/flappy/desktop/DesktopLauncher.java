package com.xinyuli.flappy.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.xinyuli.flappy.Flappy;
import com.xinyuli.flappy.utils.AndroidActionResolver;
import com.xinyuli.flappy.utils.Constants;

public class DesktopLauncher implements AndroidActionResolver {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Flappyyy";
		config.width = Constants.GAME_WIDTH;
		config.height = Constants.GAME_HEIGHT;
		new LwjglApplication(new Flappy(new DesktopLauncher()), config);
	}

	@Override
	public void showToast(CharSequence text) {

	}

	@Override
	public int getVolume() {
		return 0;
	}

	@Override
	public void signIn() {

	}

	@Override
	public void submitScore(int highScore) {

	}

	@Override
	public void showScore() {

	}

	@Override
	public boolean isSignedIn() {
		return false;
	}
}
