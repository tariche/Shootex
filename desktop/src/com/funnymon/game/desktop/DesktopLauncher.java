package com.funnymon.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.funnymon.game.IActivityRequestHandler;
import com.funnymon.game.Shootex;

public class DesktopLauncher implements IActivityRequestHandler{
	public static DesktopLauncher application;

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = Shootex.HEIGHT;
		config.width = Shootex.WIDTH;
		config.title = Shootex.title;

		if (application == null) {
			application = new DesktopLauncher();
		}
		new LwjglApplication(new Shootex(application), config);
	}

	@Override
	public void showAds(boolean show) {
		// TODO Auto-generated method stub

	}
}
