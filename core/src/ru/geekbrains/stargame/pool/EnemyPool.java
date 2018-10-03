package ru.geekbrains.stargame.pool;

import com.badlogic.gdx.audio.Sound;

import ru.geekbrains.stargame.base.SpritesPool;
import ru.geekbrains.stargame.sprites.EnemyShip;
import ru.geekbrains.stargame.sprites.MainShip;

public class EnemyPool extends SpritesPool<EnemyShip>{

    private BulletPool bulletPool;
    private Sound shootBullets;
    private MainShip mainShip;

    public EnemyPool(BulletPool bulletPool, Sound shootBullets, MainShip mainShip) {
        this.bulletPool = bulletPool;
        this.shootBullets = shootBullets;
        this.mainShip = mainShip;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(bulletPool, shootBullets, mainShip);
    }
}
