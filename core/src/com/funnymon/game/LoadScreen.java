package com.funnymon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by tarik on 3/12/17.
 */

class LoadScreen implements Screen {
    final Shootex game;

    public LoadScreen(final Shootex game) {
        this.game = game;

        Assets.background = new Texture(Gdx.files.internal("background.png"));
        Assets.hlpbackground = new Texture(Gdx.files.internal("hlpbackground.png"));
        Assets.buttons = new Texture(Gdx.files.internal("buttons.png"));
        Assets.mainmenu = new Texture(Gdx.files.internal("mainmenu.png"));
        Assets.gameover = new Texture(Gdx.files.internal("gameover.png"));
        Assets.name = new Texture(Gdx.files.internal("name.png"));
        Assets.missed = new Texture(Gdx.files.internal("missed.png"));
        Assets.rifle = new Texture(Gdx.files.internal("rifle.png"));
        Assets.airGun = new Texture(Gdx.files.internal("airGun.png"));
        Assets.hotAirBaloon = new Texture(Gdx.files.internal("hotAirBaloon.png"));
        Assets.cloud = new Texture(Gdx.files.internal("cloud.png"));
        Assets.cactus = new Texture(Gdx.files.internal("cactus.png"));
        Assets.target = new Texture(Gdx.files.internal("target.png"));
        Assets.bullet = new Texture(Gdx.files.internal("bullet.png"));
        Assets.targets = new TextureAtlas(Gdx.files.internal("target/targets.atlas"));
        Assets.shotSnd = Gdx.audio.newSound(Gdx.files.internal("shotSnd.ogg"));
        Assets.targetSnd = Gdx.audio.newSound(Gdx.files.internal("targetSnd.ogg"));
        Settings.load();

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        game.setScreen(new ManiMenuScreen(game));
        dispose();
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
