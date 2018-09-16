package ru.geekbrains.stargame.base.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private Texture backgroud;
    private Texture spaceship;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final String TITLE = "Космическая Одиссея";
    Vector2 position;
    Vector2 speed;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        backgroud = new Texture("backgroundGame.jpg");
        spaceship = new Texture("spaceship.png");
        position = new Vector2(0f,0f);
        speed = new Vector2(2f, 1f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1,0.2f,0.4f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroud,0,0,WIDTH, HEIGHT);
        batch.draw(spaceship, 0, 0);
        batch.end();
        position.add(speed);
    }

    @Override
    public void dispose() {
        batch.dispose();
        spaceship.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown screenX = " + screenX + " screenY = " + (Gdx.graphics.getHeight() - screenY));
        return super.touchDown(screenX, screenY, pointer, button);
    }
}
