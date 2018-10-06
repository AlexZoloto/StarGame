package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.List;

import ru.geekbrains.stargame.base.ActionListener;
import ru.geekbrains.stargame.base.Base2DScreen;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.EnemyPool;
import ru.geekbrains.stargame.pool.ExplosionPool;
import ru.geekbrains.stargame.sprites.Background;
import ru.geekbrains.stargame.sprites.Bullet;
import ru.geekbrains.stargame.sprites.ButtonNewGame;
import ru.geekbrains.stargame.sprites.EnemyShip;
import ru.geekbrains.stargame.sprites.Explosion;
import ru.geekbrains.stargame.sprites.MainShip;
import ru.geekbrains.stargame.sprites.MessageGameOver;
import ru.geekbrains.stargame.sprites.Star;
import ru.geekbrains.stargame.utils.EnemiesEmiter;


public class GameScreen extends Base2DScreen implements ActionListener{

    private static final int STAR_COUNT = 64;

    private enum State{PLAYING, GAME_OVER}

    Background background;
    Texture bg;
    TextureAtlas atlas;
    TextureAtlas atlasGame;

    Star[] star;
    MainShip mainShip;
    BulletPool bulletPool;

    Music music;
    Sound shootBullet;
    Sound bulletSound;
    Sound explosionSound;

    EnemyPool enemyPool;
    ExplosionPool explosionPool;

    EnemiesEmiter enemiesEmiter;

    State state;

    MessageGameOver messageGameOver;
    ButtonNewGame buttonNewGame;

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        shootBullet = Gdx.audio.newSound(Gdx.files.internal("sounds/shootBlaster.ogg"));
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Sound_19548.wav"));
        bulletSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bullet.wav"));
        music = Gdx.audio.newMusic(Gdx.files.internal("sounds/Jon_Bjork_-_The_Darkest_Hour.mp3"));
        music.setLooping(true);
        music.setVolume(0.1f);
        music.play();
        bg = new Texture("backgroundGame.jpg");
        background = new Background(new TextureRegion(bg));
        atlasGame = new TextureAtlas("textures/StarGame.tpack");
        atlas = new TextureAtlas("textures/mainAtlas.tpack");
        star = new Star[STAR_COUNT];
        for (int i = 0; i < star.length; i++) {
            star[i] = new Star(atlasGame);
        }
        bulletPool = new BulletPool();
        explosionPool = new ExplosionPool(atlas, explosionSound);
        mainShip = new MainShip(atlas, bulletPool, explosionPool, shootBullet);
        enemyPool = new EnemyPool(bulletPool, explosionPool, bulletSound, mainShip, worldBounds);
        enemiesEmiter = new EnemiesEmiter(enemyPool, atlas, worldBounds);
        messageGameOver = new MessageGameOver(atlas);
        buttonNewGame = new ButtonNewGame(atlas, this);
        startNewGame();
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
        explosionPool.updateActiveObjects(delta);
        bulletPool.updateActiveObjects(delta);

        if (mainShip.isDestroyed()){
            state = State.GAME_OVER;
        }
        switch (state){
            case PLAYING:
                mainShip.update(delta);
                enemyPool.updateActiveObjects(delta);
                enemiesEmiter.generateEnemies(delta);
                break;
            case GAME_OVER:
                break;
        }
    }

    public void checkCollisions() {
        //Столкновение кораблей
        List<EnemyShip> enemyList = enemyPool.getActiveObjects();
        for (EnemyShip enemyShip : enemyList){
            if (enemyShip.isDestroyed()){
                continue;
            }
            float minDist = enemyShip.getHalfWidth() + mainShip.getHalfWidth();
            if (enemyShip.pos.dst2(mainShip.pos) < minDist * minDist){
                enemyShip.destroy();
                enemyShip.explosionShip();
                mainShip.damage(10 * enemyShip.getBulletDamage());
                return;
            }
        }
        //Столкновение вражеских кораблей с нашей пулей
        List<Bullet> bulletList = bulletPool.getActiveObjects();
        for(EnemyShip enemyShip : enemyList){
            if (enemyShip.isDestroyed()){
                continue;
            }
            for (Bullet bullet : bulletList){
                if (bullet.getOwner() != mainShip || bullet.isDestroyed()){
                    continue;
                }
                if (enemyShip.isBulletCollision(bullet)){
                    bullet.destroy();
                    enemyShip.damage(bullet.getDamage());
                    return;
                }
            }
        }
        //Столкновение игрового корабля с пулей
        for (Bullet bullet: bulletList){
            if (bullet.getOwner() == mainShip || bullet.isDestroyed()){
                continue;
            }
            if (mainShip.isBulletCollision(bullet)){
                mainShip.damage(bullet.getDamage());
                bullet.destroy();
            }
        }

    }

    public void deleteAllDestroyed() {
        bulletPool.freeAllDestroyedActiveObjects();
        enemyPool.freeAllDestroyedActiveObjects();
        explosionPool.freeAllDestroyedActiveObjects();
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
        enemyPool.drawActiveObjects(batch);
        explosionPool.drawActiveObjects(batch);
        if (state == State.GAME_OVER){
            messageGameOver.draw(batch);
            buttonNewGame.draw(batch);
        }
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
        shootBullet.dispose();
        enemyPool.dispose();
        explosionPool.dispose();
        music.dispose();
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (state == State.PLAYING){
            mainShip.keyDown(keycode);
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        if (state == State.PLAYING){
            mainShip.keyUp(keycode);
        }
        return super.keyUp(keycode);
    }


    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (state == State.PLAYING){
            mainShip.touchDown(touch, pointer);
        }
        if (state == State.GAME_OVER){
            buttonNewGame.touchDown(touch, pointer);
        }
        return super.touchDown(touch, pointer);
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (state == State.PLAYING){
            mainShip.touchUp(touch, pointer);
        }
        if (state == State.GAME_OVER){
            buttonNewGame.touchUp(touch, pointer);
        }
        return super.touchUp(touch, pointer);
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    private  void startNewGame(){
        state = State.PLAYING;

        mainShip.startNewGame();

        bulletPool.freeeAllActiveObjects();
        explosionPool.freeeAllActiveObjects();
        enemyPool.freeeAllActiveObjects();
    }

    @Override
    public void actionPerformed(Object src) {
        if (src == buttonNewGame){
            startNewGame();
        }
    }
}
