package com.funnymon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by tarik on 3/13/17.
 */

class GameScreen implements Screen {
    final Shootex game;
    private OrthographicCamera camera;

    private enum GameStatus {
        Running,
        GameOver
    }
    private GameStatus status = GameStatus.Running;

    private World world;
    private int oldScore = 0;
    private String printScore = "0";
    private static final String TEXTSCORE = "SCORE: ";
    private static final String TEXTHIGHSCORE = "TOP SCORE: ";
    private TextureAtlas textureAtlas;
    private Animation<TextureRegion> animation;
    private int letterHight;
    private boolean fire;

    private float scoreCount;
    private String gameOverScore = new String();

    GameScreen(final Shootex game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Shootex.WIDTH, Shootex.HEIGHT);
        world = new World();
        textureAtlas = new TextureAtlas(Gdx.files.internal("target/targets.atlas"));
        animation = new Animation<TextureRegion>(0.05f, textureAtlas.getRegions());
        letterHight = (int) game.font.getCapHeight();
        fire = false;
        scoreCount = 0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();

        game.batch.draw(Assets.background, 0, 0);
        if (fire) {
            game.batch.draw(Assets.airGun, 152, 0, 176, 215);
            fire = false;
        } else {
            game.batch.draw(Assets.airGun, 152, 0);
        }
        game.batch.draw(Assets.cactus, 340, 300);
        game.batch.draw(Assets.cloud, Shootex.cloud1.x, Shootex.cloud1.y);
        game.batch.draw(Assets.cloud, Shootex.cloud2.x, Shootex.cloud2.y);
        game.batch.draw(Assets.hotAirBaloon, Shootex.hotAirBallon.x, Shootex.hotAirBallon.y);

        if (status == GameStatus.GameOver) {
            drawGameOver(delta);
        } else {
            drawRunning(delta);
        }

        game.batch.end();

        Shootex.cloud1.move();
        Shootex.cloud2.move();
        Shootex.hotAirBallon.move();

        Vector3 touchPos = new Vector3();
        if (Gdx.input.justTouched()) {
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

        }

        if (status == GameStatus.Running) {
            updateRunning(touchPos, delta);
        }
        if (status == GameStatus.GameOver) {
            updateGameOver(touchPos);
        }
    }

    private void updateRunning(Vector3 touchPos, float delta) {
        if (touchPos.x > 152 && touchPos.x < 330 && touchPos.y < 200) {
            Gdx.input.vibrate(100);
            if (Settings.soundEnabled) {
                Assets.shotSnd.play(1);
                fire = true;
            }
            world.shot = true;
        }


        world.update(delta);

        if (world.gameOver) {
            status = GameStatus.GameOver;
        }

        if (oldScore != world.score) {
            oldScore = world.score;
        }
    }

    private void updateGameOver(Vector3 touchPos) {
         if (touchPos.x > 165 && touchPos.x < 315) {
            if (touchPos.y > 342 && touchPos.y < 400) {
                Shootex.myRequestHandler.showAds(false);
                game.setScreen(new GameScreen(game));
            }
            if (touchPos.y > 284 && touchPos.y < 341) {
                Settings.save();
                game.setScreen(new HelpScreen(game));
            }
            if (touchPos.y > 226 && touchPos.y < 283) {
                Settings.save();
                game.setScreen(new ManiMenuScreen(game));
            }
        }
    }


    private void drawRunning(float delta) {
        int len = world.targetQue.targets.size(); //TODO move to mebers and intiate once
        for (int i = 0; i < len; i++) {
            Target target = world.targetQue.targets.get(i);
            if (target.isVisible) {
                game.batch.draw(Assets.target, target.x, target.y);
            }
            if (target.isShot) {
                target.elapsedTime += delta;
                game.batch.draw(animation.getKeyFrame(target.elapsedTime, false), target.x, target.y);
            }
            if (animation.isAnimationFinished(target.elapsedTime)) {
                target.elapsedTime = 0;
                target.isShot = false;
            }
        }

        int bulletsLen = world.bullets.size();
        if (bulletsLen != 0) {
            for (int i = 0; i < bulletsLen; i++) {
                Bullet bullet = world.bullets.get(i);
                game.batch.draw(Assets.bullet, bullet.x - 4, bullet.y - 4, 8, 16);
            }
        }

        drawCurrentScore();
    }

    private void drawCurrentScore() {
        if (Integer.parseInt(printScore) != oldScore) {
            printScore = "" + oldScore;
        }
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, TEXTSCORE + printScore, 20, 780);
        game.font.draw(game.batch, TEXTHIGHSCORE + Settings.highScore, 20 , 775 - letterHight);
    }

    private void drawGameOver(float delta) {
        drawFinalScore(delta);
        game.batch.draw(Assets.missed, 145, 536);
        game.batch.draw(Assets.gameover, 165, 226);
    }

    public void drawFinalScore(float delta) {
        scoreCount = Math.min((scoreCount + delta*100), (float)oldScore);
        gameOverScore = String.format(TEXTSCORE + "%04d", (int)scoreCount);
        game.font.draw(game.batch, gameOverScore, Gdx.graphics.getWidth()/2, 700);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Settings.save();
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
