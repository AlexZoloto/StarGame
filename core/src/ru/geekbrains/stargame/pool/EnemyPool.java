package ru.geekbrains.stargame.pool;

import com.badlogic.gdx.audio.Sound;

import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.sprites.EnemyShip;
import ru.geekbrains.stargame.sprites.MainShip;

public class EnemyPool extends SpritesPool<EnemyShip>{

    private BulletPool bulletPool;
    private ExplosionPool explosionPool;
    private Sound shootBullets;
    private MainShip mainShip;
    private Rect worldBounds;

    Sound explosionSound;

    public EnemyPool(BulletPool bulletPool,ExplosionPool explosionPool ,Sound shootBullets, MainShip mainShip, Rect worldBounds) {
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.shootBullets = shootBullets;
        this.mainShip = mainShip;
        this.worldBounds = worldBounds;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, shootBullets, mainShip, worldBounds, explosionPool);
    }
}
