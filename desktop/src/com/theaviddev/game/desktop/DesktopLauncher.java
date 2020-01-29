package com.theaviddev.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import dev.theavid.game.Worshipper;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Game";
		config.height = 1080;
		config.width = 1920;
		new LwjglApplication(new Worshipper(), config);
	}
}
