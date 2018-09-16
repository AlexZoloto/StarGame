package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;

import ru.geekbrains.stargame.base.screen.MenuScreen;

public class StarGame extends Game{
	@Override
	public void create() {
		setScreen(new MenuScreen(this));
	}
}
