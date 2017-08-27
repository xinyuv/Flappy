package com.xinyuli.flappy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.games.Games;
import com.google.example.games.basegameutils.GameHelper;
import com.xinyuli.flappy.utils.AndroidActionResolver;

/**
 * The android launcher that sets up all needed android objects and pass them into the core game.
 */
public class AndroidLauncher extends AndroidApplication implements AndroidActionResolver {

	private Recorder recorder;
	private GameHelper gameHelper;
	private Toast toast;
	private final static int requestCode = 1;
	private static final int msgWait = 0x1001;
	private static final int refreshTime = 100;
	private float volume = 10000;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		setUpGame();
		recorder = new Recorder();
		initialize(new Flappy(this), config);
	}

	/**
	 * Set up google play game service.
	 */
	private void setUpGame() {
		gameHelper = new GameHelper(this, GameHelper.CLIENT_GAMES);
		gameHelper.enableDebugLog(false);
		GameHelper.GameHelperListener gameHelperListener = new GameHelper.GameHelperListener() {
			@Override
			public void onSignInFailed(){
			}

			@Override
			public void onSignInSucceeded(){
			}
		};
		gameHelper.setup(gameHelperListener);
	}

	@Override
	protected void onStart() {
		super.onStart();
		gameHelper.onStart(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		try {
			gameHelper.onStop();
			recorder.stopRecording();
			vHandler.removeMessages(msgWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pause the handler by removing all messages. **Important**
	 */
	@Override
	protected void onPause() {
		super.onPause();
		try {
			recorder.stopRecording();
			vHandler.removeMessages(msgWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		startRecording();		// safe because startRecording handles permission errors
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		try {
			recorder.stopRecording();
			vHandler.removeMessages(msgWait);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		gameHelper.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void signIn() {
		try {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					gameHelper.beginUserInitiatedSignIn();
				}
			});
		} catch (Exception e) {
			Gdx.app.log("MainActivity", "Log in failed: " + e.getMessage() + ".");
			Toast.makeText(this, "Failed to log in to Google Play", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public void submitScore(int highScore) {
		if (isSignedIn()) {
			Games.Leaderboards.submitScore(gameHelper.getApiClient(),
					getString(R.string.leaderboard_flappy), highScore);
		}
	}

	@Override
	public void showScore() {
		if (isSignedIn()) {
			startActivityForResult(Games.Leaderboards.getLeaderboardIntent(gameHelper.getApiClient(),
					getString(R.string.leaderboard_flappy)), requestCode);
		} else {
			signIn();
		}
	}

	@Override
	public boolean isSignedIn() {
		return gameHelper.isSignedIn();
	}

	/**
	 * Use a new handler to show toast. Only one toast is shown at a time.
	 * @param text The text goes in the toast.
	 */
	@Override
	public void showToast(final CharSequence text) {
		final Context context = this;
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (toast == null) {
					toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
				}
				if (!toast.getView().isShown()) {
					toast.setText(text);
					toast.show();
				}
			}

		});
	}

	@Override
	public int getVolume() {
		return (int)volume;
	}

	/**
	 * Separate thread for update volume. Has refresh threshold.
	 */
	private Handler vHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if (this.hasMessages(msgWait)) {
				return;
			}
			volume = recorder.getMaxAmplitude();
			vHandler.sendEmptyMessageDelayed(msgWait, refreshTime);
		}
	};

	/**
	 * Send message to handler to request for volume update.
	 */
	private void startListening() {
		vHandler.sendEmptyMessageDelayed(msgWait, refreshTime);
	}

	/**
	 * Will pop up error toast if exceptions are thrown.
	 */
	public void startRecording() {
		try {
			if (recorder.startRecording()) {
				startListening();
			} else {
				Toast.makeText(this, "Failed to get sound input", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(this, "No permit to the recorder", Toast.LENGTH_SHORT).show();
			e.printStackTrace();
		}
	}
}
