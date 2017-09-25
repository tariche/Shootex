package com.funnymon.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Shootex extends Game {
    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String title = "Shootex";
    static RandomlyMovingObject cloud1;
    static RandomlyMovingObject cloud2;
    static RandomlyMovingObject hotAirBallon;

    SpriteBatch batch;
//    BitmapFont font;
    BitmapFont font30;
    BitmapFont font40;
    FreeTypeFontGenerator generator;

    static IActivityRequestHandler myRequestHandler;

    public Shootex(IActivityRequestHandler handler) {
        myRequestHandler = handler;
        cloud1 = new RandomlyMovingObject(50f, 500f);
        cloud2 = new RandomlyMovingObject(240f, 570f);
        hotAirBallon = new RandomlyMovingObject(64f, 480f, 200);

    }

    @Override
    public void create() {
        batch = new SpriteBatch();
//        font = new BitmapFont(Gdx.files.internal("font/arial-15.fnt"),false);
        generator = new FreeTypeFontGenerator(Gdx.files.internal("font/opensans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.WHITE;
        parameter.borderWidth = 1;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        parameter.shadowColor = new Color(0, 0.5f, 0, 0.75f);
        parameter.size = 20;
        font30 = generator.generateFont(parameter);
        parameter.size = 40;
        font40 = generator.generateFont(parameter);
        this.setScreen(new LoadScreen(this));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
//        font.dispose();
        generator.dispose();
        font30.dispose();
        font40.dispose();
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
