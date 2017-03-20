package com.funnymon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * Created by tarik on 3/19/17.
 */

public class Settings {
    public static final String  SOUNDON = "soundON";
    public static final String HIGHSCORE = "highScore";
    public static boolean soundEnabled;
    public static int highScore;
    private static Preferences prefs = Gdx.app.getPreferences("Shootex");

    public static void load() {
        soundEnabled = prefs.getBoolean(SOUNDON, true);
        highScore = prefs.getInteger(HIGHSCORE, 0);
    }

    public static void save() {
        prefs.putBoolean(SOUNDON, soundEnabled);
        prefs.putInteger(HIGHSCORE, highScore);
        prefs.flush();
    }
}
