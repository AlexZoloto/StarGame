package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture backgroud;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Космическая Одиссея";
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		backgroud = new Texture("backgroundGame.jpg");
	}

	@Override
	public void render () {
		batch.begin();
		batch.draw(backgroud,0,0,WIDTH, HEIGHT);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
