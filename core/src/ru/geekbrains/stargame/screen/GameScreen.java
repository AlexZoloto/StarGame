package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.sprites.Background;
import ru.geekbrains.stargame.sprites.MainShip;
import ru.geekbrains.stargame.sprites.Star;


public class GameScreen extends Base2DScreen {

    private static final int STAR_COUNT = 64;

    Background background;
    Texture bg;
    TextureAtlas atlas;
    TextureAtlas atlasGame;

    Star[] star;
    MainShip mainShip;
    BulletPool bulletPool;

    Music music;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("backgroundGame.jpg");
        background = new Background(new TextureRegion(bg));
        atlasGame = new TextureAtlas("textures/StarGame.tpack");
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlasGame);
        }
        bulletPool = new BulletPool();
        mainShip = new MainShip(atlas, bulletPool);
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Jon_Bjork_-_The_Darkest_Hour.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        checkCollisions();
        deleteAllDestroyed();
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < star.length; i++) {
            star[i].update(delta);
        }
        mainShip.update(delta);
        bulletPool.updateActiveObjects(delta);
    }

    public void checkCollisions() {

    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
    }

    public void draw() {
        Gdx.gl.glClearColor(1, 0.4f, 0.6f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < star.length; i++) {
            star[i].draw(batch);
        }
        mainShip.draw(batch);
        bulletPool.drawActiveObjects(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < star.length; i++) {
            star[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlasGame.dispose();
        bulletPool.dispose();
        mainShip.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        mainShip.keyDown(keycode);
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        mainShip.keyUp(keycode);
        return super.keyUp(keycode);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        mainShip.touchDown(touch, pointer);
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        mainShip.touchUp(touch, pointer);
        return super.touchUp(touch, pointer);
    }

}
