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

import java.util.List;

/**
 * Created by tarik on 3/13/17.
 */

class GameScreen implements Screen {
    final Shootex game;
    OrthographicCamera camera;

    enum GameStatus {
        Running,
        GameOver;
    }
    GameStatus status = GameStatus.Running;

    World world;
    int oldScore = 0;
    String printScore = "0";
    String textScore = "SCORE: ";
    int textScoreHeight;
    private TextureAtlas textureAtlas;
    private Animation animation;
    private float elapsedTime = 0;

    public GameScreen(final Shootex game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
        world = new World();
        textureAtlas = new TextureAtlas(Gdx.files.internal("target/targets.atlas"));
        animation = new Animation(1/20f, textureAtlas.getRegions());
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

        if (status == GameStatus.GameOver) {
            drawGameOver();
        } else {
            drawRunning();
        }

        game.batch.end();

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
            if (game.soundEnabled) {
                Assets.shotSnd.play(1);
            }
            world.shot = true;
        }


        world.update(delta);

        if (world.gameOver) {
            status = GameStatus.GameOver;
        }

        if (oldScore != world.score) {
            oldScore = world.score;
            if (game.soundEnabled) {
                Assets.targetSnd.play(1);
            }
        }
    }

    private void updateGameOver(Vector3 touchPos) {
        /*if (touchPos.x > 81 && touchPos.y < 81) {
            game.soundEnabled = !game.soundEnabled;
            if (game.soundEnabled) {
                Assets.shotSnd.play(1);
            }
        }*/
        if (touchPos.x > 165 && touchPos.x < 315) {
            if (touchPos.y > 342 && touchPos.y < 400) {
                game.setScreen(new GameScreen(game));
            }
            if (touchPos.y > 284 && touchPos.y < 341) {
                game.setScreen(new HelpScreen(game));
            }
            if (touchPos.y > 226 && touchPos.y < 283) {
                game.setScreen(new ManiMenuScreen(game));
            }
        }
    }


    private void drawRunning() {
        int len = world.targetQue.targets.size();
        for (int i = 0; i < len; i++) {
            Target target = world.targetQue.targets.get(i);
            if (target.isVisible) {
                game.batch.draw(Assets.target, target.x, target.y);
            }
            if (target.isShot && elapsedTime < 4/10f) {
                elapsedTime += Gdx.graphics.getDeltaTime();
                game.batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, false), target.x, target.y);
            }
            if (elapsedTime > 4/20f) {
                elapsedTime = 0;
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
//        game.font.draw(game.batch, textScore, 20, 780);
        if (Integer.parseInt(printScore) != oldScore) {
            printScore = "" + oldScore;
        }
        game.font.setColor(Color.BLACK);
        game.font.draw(game.batch, textScore + printScore, 20, 780);
//        g.drawText(printScore, 20, 10 + 2 * textScoreHeight, paint);
    }

    private void drawGameOver() {
        game.batch.draw(Assets.missed, 145, 536);
        game.batch.draw(Assets.gameover, 165, 226);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

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
