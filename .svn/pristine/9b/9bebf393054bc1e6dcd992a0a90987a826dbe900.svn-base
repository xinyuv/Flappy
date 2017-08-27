package com.xinyuli.flappy.gameworlds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.xinyuli.flappy.gameobjects.Bird;
import com.xinyuli.flappy.gameobjects.City;
import com.xinyuli.flappy.gameobjects.Ground;
import com.xinyuli.flappy.gameobjects.ScrollHandler;
import com.xinyuli.flappy.gameobjects.Tube;
import com.xinyuli.flappy.utils.AssetLoader;
import com.xinyuli.flappy.utils.Constants;

/**
 * Created by xinyuli on 09/04/2017.
 * <p>
 * Renders the game attributes.
 */

public class GameRenderer {

    private GameWorld world;
    private OrthographicCamera cam;

    private SpriteBatch batch;

    private Bird bird;
    private City city1, city2;
    private Ground ground1, ground2;
    private Tube tube1, tube2, tube3;

    private Texture bg, nightBg, title, ground, topTube, botTube, scoreBoard, playBtn,
            leaderBoardBtn, settingBtn, blink;
    private TextureRegion birdRegion;

    private Animation<TextureRegion> birdAnimation;


    public GameRenderer(GameWorld world) {
        this.world = world;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        initGameObjects();
        initAssets();
    }

    /**
     * Render all the objects to the screen.
     */
    public void render(float dt) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.disableBlending();        // no transparency needed
        drawCity();
        batch.enableBlending();

        drawTube();
        drawGround();
        drawBird(dt);

        if (world.isReady()) {
            drawTitle();
            drawButtons();
        } else if (world.isRunning()) {
            drawScore();
        } else if (world.isGameOver()){
            drawHighScore();
            drawButtons();
        }

        if (!world.isTouchMode() && world.showSoundValue()) {
            drawSoundValue();
        }

        batch.end();
    }

    /**
     * Initialize the game objects.
     */
    private void initGameObjects() {
        bird = world.getBird();
        ScrollHandler scrollHandler = world.getScrollHandler();
        city1 = scrollHandler.getCity1();
        city2 = scrollHandler.getCity2();
        ground1 = scrollHandler.getGround1();
        ground2 = scrollHandler.getGround2();
        tube1 = scrollHandler.getTube1();
        tube2 = scrollHandler.getTube2();
        tube3 = scrollHandler.getTube3();
    }

    /**
     * Get the assets from the AssetLoder.
     */
    private void initAssets() {
        bg = AssetLoader.bg;
        nightBg = AssetLoader.nightBg;
        title = AssetLoader.title;
        ground = AssetLoader.ground;
        scoreBoard = AssetLoader.scoreBoard;
        playBtn = AssetLoader.playBtn;
        leaderBoardBtn = AssetLoader.leaderBoardBtn;
        settingBtn = AssetLoader.settingBtn;
        topTube = AssetLoader.topTube;
        botTube = AssetLoader.botTube;
        birdRegion = AssetLoader.birdRegion;
        blink = AssetLoader.blink;
        birdAnimation = AssetLoader.birdAnimation;
    }

    private void drawTitle() {
        batch.draw(title,
                cam.position.x - (int) (title.getWidth() * 0.5 * 1.5), cam.position.y + 50,
                (int)(title.getWidth() * 1.5), (int)(title.getHeight() * 1.5));
    }

    private void drawBird(float dt) {
        if (bird.shouldFlap()) {
            batch.draw(birdAnimation.getKeyFrame(dt), bird.getX(), bird.getY(),
                    bird.getWidth() / 2, bird.getHeight() / 2,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        } else {
            batch.draw(birdRegion, bird.getX(), bird.getY(),
                    bird.getWidth() / 2, bird.getHeight() / 2,
                    bird.getWidth(), bird.getHeight(), 1, 1, bird.getRotation());
        }
    }

    private void drawCity() {
//        boolean isDay = (world.getScore()/10) % 2 == 0 ^ world.isTouchMode();
        boolean isDay = !world.isTouchMode();
        if (isDay) {
            batch.draw(bg, city1.getX(), city1.getY());
            batch.draw(bg, city2.getX(), city2.getY());
        } else {
            batch.draw(nightBg, city1.getX(), city1.getY(),
                    Constants.BG_WIDTH, Constants.BG_HEIGHT);
            batch.draw(nightBg, city2.getX(), city2.getY(),
                    Constants.BG_WIDTH, Constants.BG_HEIGHT);
        }
    }

    private void drawGround() {
        batch.draw(ground, ground1.getX(), ground1.getY());
        batch.draw(ground, ground2.getX(), ground2.getY());
    }

    private void drawTube() {
        batch.draw(topTube, tube1.getX(), tube1.getY());
        batch.draw(botTube, tube1.getX(), tube1.getBotY());

        batch.draw(topTube, tube2.getX(), tube2.getY());
        batch.draw(botTube, tube2.getX(), tube2.getBotY());

        batch.draw(topTube, tube3.getX(), tube3.getY());
        batch.draw(botTube, tube3.getX(), tube3.getBotY());
    }

    private void drawButtons() {
        batch.draw(playBtn, cam.position.x - (int) (scoreBoard.getWidth() * 0.35),
                cam.position.y - (int) (scoreBoard.getHeight() * 0.35),
                (int) (playBtn.getWidth() * 0.7), (int) (playBtn.getHeight() * 0.7));

        batch.draw(leaderBoardBtn, cam.position.x + 10,
                cam.position.y - (int) (scoreBoard.getHeight() * 0.35),
                (int) (playBtn.getWidth() * 0.7), (int) (playBtn.getHeight() * 0.7));

        batch.draw(settingBtn, cam.position.x - (int)(settingBtn.getWidth() * 0.75),
                cam.position.y - (int) (scoreBoard.getHeight() * 0.35)
                        - (int) (settingBtn.getHeight() * 1.5) - 10,
                (int) (settingBtn.getWidth() * 1.5), (int) (settingBtn.getHeight() * 1.5));
    }

    private void drawScore() {
        String score = world.getScore() + "";
        AssetLoader.font.getData().setScale(0.5f);
        AssetLoader.shadow.getData().setScale(0.5f);
        AssetLoader.shadow.draw(batch, score, cam.position.x - score.length() * 10,
                cam.viewportHeight * 3 / 4);
        AssetLoader.font.draw(batch, score, cam.position.x - score.length() * 10 - 1,
                cam.viewportHeight * 3 / 4 - 1);
    }

    private void drawHighScore() {
        batch.draw(scoreBoard, cam.position.x - (int) (scoreBoard.getWidth() * 0.35),
                cam.position.y - (int) (scoreBoard.getHeight() * 0.35),
                (int) (scoreBoard.getWidth() * 0.7), (int) (scoreBoard.getHeight() * 0.7));

        String highScore = AssetLoader.getHighScore() + "";
        String score = world.getScore() + "";
        AssetLoader.font.getData().setScale(0.25f);
        AssetLoader.shadow.getData().setScale(0.25f);
        AssetLoader.shadow.draw(batch, score, cam.position.x + 50, cam.position.y + 25);
        AssetLoader.font.draw(batch, score, cam.position.x + 50 - 1, cam.position.y + 24);
        AssetLoader.shadow.draw(batch, highScore, cam.position.x + 50, cam.position.y - 5);
        AssetLoader.font.draw(batch, highScore, cam.position.x + 50 - 1, cam.position.y - 6);

        if (world.isNewHighScore()) {
            batch.draw(blink, cam.position.x + 23, cam.position.y - 10);
        }
    }

    private void drawSoundValue() {
        AssetLoader.mozart.draw(batch, String.valueOf("Sound value: " + world.getVolume()),
                100 - 57, cam.position.y - 170);
    }

    public OrthographicCamera getCam() {
        return cam;
    }
}
