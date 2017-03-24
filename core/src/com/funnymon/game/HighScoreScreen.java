package com.funnymon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by tarik on 3/13/17.
 */

class HighScoreScreen implements Screen {
    final Shootex game;
    OrthographicCamera camera;

    public HighScoreScreen(final Shootex game) {
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
        game.batch.draw(Assets.cloud, Shootex.cloud1.x, Shootex.cloud1.y);
        game.batch.draw(Assets.cloud, Shootex.cloud2.x, Shootex.cloud2.y);
        game.batch.draw(Assets.hotAirBaloon, Shootex.hotAirBallon.x, Shootex.hotAirBallon.y);
        game.batch.draw(Assets.buttons, 0, 719, 80, 80, 81, 81);

        game.batch.end();

        Shootex.cloud1.move();
        Shootex.cloud2.move();

        if (Gdx.input.justTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            if (touchPos.x < 80 && touchPos.y > 719) {
                game.setScreen(new ManiMenuScreen(game));
                dispose();
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
