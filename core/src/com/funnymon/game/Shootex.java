package com.funnymon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Shootex extends Game {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String title = "Shootex";

    public static boolean soundEnabled = true;


    public SpriteBatch batch;
    public BitmapFont font;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("font/arial-15.fnt"),false);
        this.setScreen(new LoadScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}


/*
public class Shootex extends ApplicationAdapter {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;

    public static final String title = "Shootex";

    private SpriteBatch batch;
    private Texture img;
    private Sprite sprite;
    private TextureAtlas textureAtlas;
    private Sprite targetSprite;
    private Animation animation;
    private float elapsedTime = 0;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture(Gdx.files.internal("badlogic.jpg"));
        sprite = new Sprite(img);
        textureAtlas = new TextureAtlas(Gdx.files.internal("target/targets.atlas"));
        animation = new Animation(1/10f, textureAtlas.getRegions());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        sprite.draw(batch);
        elapsedTime += Gdx.graphics.getDeltaTime();
        batch.draw((TextureRegion) animation.getKeyFrame(elapsedTime, true), 0, Gdx.graphics.getHeight() - 96);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        textureAtlas.dispose();
    }
}
*/
