package ru.geekbrains.stargame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ru.geekbrains.stargame.StarGame;
import ru.geekbrains.stargame.base.screen.MenuScreen;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = MenuScreen.WIDTH;
		config.height = MenuScreen.HEIGHT;
		config.title = MenuScreen.TITLE;
		new LwjglApplication(new StarGame(), config);
	}
}
