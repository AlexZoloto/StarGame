package ru.geekbrains.stargame.base.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    private Vector2 startPosition;
    private Vector2 speed;
    private float positionX;
    private float positionY;


    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        backgroud = new Texture("backgroundGame.jpg");
        spaceship = new Texture("spaceship.png");
        positionX = 0;
        positionY = 0;
        startPosition = new Vector2(0,0);
        speed = new Vector2(2f, 4f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(1,0.2f,0.4f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroud,0,0,WIDTH, HEIGHT);
        batch.draw(spaceship, startPosition.x, startPosition.y);
        batch.end();
        changePosition(startPosition, positionX, positionY, speed);
        movesWhenKeyPressed();
    }

    @Override
    public void dispose() {
        batch.dispose();
        spaceship.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        positionX = screenX;
        positionY = (Gdx.graphics.getHeight() - screenY);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) positionX -= speed.x;
        if (keycode == Input.Keys.RIGHT) positionX += speed.x;
        if (keycode == 19) positionY += speed.y;
        if (keycode == 20) positionY -= speed.y;
        return super.keyDown(keycode);
    }
    private void movesWhenKeyPressed(){
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            positionX -= speed.x;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            positionX += speed.x;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            positionY -= speed.y;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            positionY += speed.y;
        }
    }
}