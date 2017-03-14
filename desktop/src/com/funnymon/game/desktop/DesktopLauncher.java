package com.funnymon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.funnymon.game.Shootex;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Shootex.HEIGHT;
		config.width = Shootex.WIDTH;
		config.title = Shootex.title;
		new LwjglApplication(new Shootex(), config);
	}
}
