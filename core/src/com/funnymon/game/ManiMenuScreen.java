package com.funnymon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by tarik on 3/12/17.
 */

class ManiMenuScreen implements Screen {
    final Shootex game;
    OrthographicCamera camera;

    public ManiMenuScreen(final Shootex game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
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
        game.batch.draw(Assets.airGun, 152, 0);
        game.batch.draw(Assets.cactus, 340, 300);
        game.batch.draw(Assets.cloud, Shootex.cloud1.x, Shootex.cloud1.y);
        game.batch.draw(Assets.cloud, Shootex.cloud2.x, Shootex.cloud2.y);
        game.batch.draw(Assets.hotAirBaloon, Shootex.hotAirBallon.x, Shootex.hotAirBallon.y);
//        game.batch.draw(Assets.hotAirBaloon, 64, 480);
        game.batch.draw(Assets.name, 130, 480);
        game.batch.draw(Assets.mainmenu, 100, 210);
        if (Settings.soundEnabled) {
            game.batch.draw(Assets.buttons, 0, 719, 80, 0, 81, 81);
        } else {
            game.batch.draw(Assets.buttons, 0, 719, 0, 0, 81, 81);
        }

        game.batch.end();

        Shootex.cloud1.move();
        Shootex.cloud2.move();
        Shootex.hotAirBallon.move();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);

            if (touchPos.x < 80 && touchPos.y > 719) {
                Settings.soundEnabled = !Settings.soundEnabled;
                Settings.save();
                if (Settings.soundEnabled) {
                    Assets.shotSnd.play(1);
                }
            }

            if (touchPos.x > 100 && touchPos.x < 400) {
                if (touchPos.y > 339 && touchPos.y < 402) {
                    System.out.println("Start game");
                    Shootex.myRequestHandler.showAds(false);
                    game.setScreen(new GameScreen(game));
                    dispose();
                }
                if (touchPos.y > 275 && touchPos.y < 338) {
                    System.out.println("High score");
                    game.setScreen(new HighScoreScreen(game));
                    dispose();
                }
                if (touchPos.y > 210 && touchPos.y < 274) {
                    System.out.println("Help");
                    game.setScreen(new HelpScreen(game));
                    dispose();
                }
            }

        }

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
