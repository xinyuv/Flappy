package com.xinyuli.flappy.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Array;


/**
 * Created by xinyuli on 10/04/2017.
 *
 * Load game texture and sounds. Only load them once since it is an expensive procedure.
 */
public class AssetLoader {
    public static Texture bg, nightBg, title, ground, splash, scoreBoard,
            playBtn, leaderBoardBtn, settingBtn, blink;
    public static Texture topTube, botTube;
    public static TextureRegion birdRegion;
    public static Animation<TextureRegion> birdAnimation;
    public static Sound hit, die, wing, point;
    public static BitmapFont font, shadow, mozart;

    private static Preferences preferences;

    /**
     * Load the assets.
     */
    public static void load() {
        bg = new Texture("bg.png");
        nightBg = new Texture("nightbg.png");
        title = new Texture("title.png");
        ground = new Texture("ground.png");
        splash = new Texture("splash.png");
        scoreBoard = new Texture("scoreboard.png");
        topTube = new Texture("toptube.png");
        botTube = new Texture("bottomtube.png");
        birdRegion = new TextureRegion(new Texture("bird.png"));
        playBtn = new Texture("playBtn.png");
        leaderBoardBtn = new Texture("leaderboardBtn.png");
        settingBtn = new Texture("setting.png");
        blink = new Texture("new.png");

        Texture texture = new Texture("birdanimation.png");
        Array<TextureRegion> frames = new Array<TextureRegion>();
        int frameWidth = texture.getWidth() / 3;
        for (int i = 0; i < 3; i++) {
            frames.add(new TextureRegion(texture, i*frameWidth, 0, frameWidth, texture.getHeight()));
        }
        birdAnimation = new Animation<TextureRegion>(0.25f, frames, Animation.PlayMode.LOOP_PINGPONG);

        hit = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_hit.mp3"));
        die = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_die.mp3"));
        wing = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_wing.mp3"));
        point = Gdx.audio.newSound(Gdx.files.internal("sounds/sfx_point.mp3"));

        font = new BitmapFont(Gdx.files.internal("font/text.fnt"));
        font.getData().setScale(0.5f);
        shadow = new BitmapFont(Gdx.files.internal("font/shadow.fnt"));
        shadow.getData().setScale(0.5f);

        FreeTypeFontGenerator generator =
                new FreeTypeFontGenerator(Gdx.files.internal("font/emulogic.ttf"));

        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = 10;
        parameter.color = Color.WHITE;
        parameter.shadowColor = Color.BLACK;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        mozart = generator.generateFont(parameter);
        generator.dispose();

        preferences = Gdx.app.getPreferences("Flappy");
        if (!preferences.contains("highScore")) {
            preferences.putInteger("highScore", 0);
        }
    }

    /**
     * Set high score.
     * @param highScore The new high score.
     */
    public static void setHighScore(int highScore) {
        preferences.putInteger("highScore", highScore);
        preferences.flush();
    }

    public static int getHighScore() {
        return preferences.getInteger("highScore");
    }


    /**
     * Dispose the texture so there will be no memory leaks.
     */
    public static void dispose() {
        bg.dispose();
        nightBg.dispose();
        title.dispose();
        ground.dispose();
        splash.dispose();
        scoreBoard.dispose();
        playBtn.dispose();
        leaderBoardBtn.dispose();
        settingBtn.dispose();
        blink.dispose();
        topTube.dispose();
        botTube.dispose();
        hit.dispose();
        die.dispose();
        wing.dispose();
        point.dispose();
        font.dispose();
        shadow.dispose();
        mozart.dispose();
    }
}
