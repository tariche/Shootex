package com.funnymon.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;


/**
 * Created by tarik on 3/19/17.
 */

class Settings {
    static final String  SOUNDON = "soundON";
    static final String HIGHSCORE = "highScore";
    static boolean soundEnabled;
    static int highScore;
    private static Preferences prefs = Gdx.app.getPreferences("Shootex");

    static void load() {
        soundEnabled = prefs.getBoolean(SOUNDON, true);
        highScore = prefs.getInteger(HIGHSCORE, 0);
    }

    static void save() {
        prefs.putBoolean(SOUNDON, soundEnabled);
        prefs.putInteger(HIGHSCORE, highScore);
        prefs.flush();
    }
}
