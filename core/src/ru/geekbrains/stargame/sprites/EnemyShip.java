package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.ExplosionPool;

public class EnemyShip extends Ship {

    private enum State {DESCENT, FIGHT}

    private MainShip mainShip;
    private Vector2 v0 = new Vector2();
    private Vector2 descentV = new Vector2(0, -0.15f);
    private ExplosionPool explosionPool;

    private State state;

    public EnemyShip(BulletPool bulletPool, Sound shootSound, MainShip mainShip, Rect worldBounds, ExplosionPool explosionPool) {
        super(bulletPool, explosionPool, shootSound);
        this.mainShip = mainShip;
        this.v.set(v0);
        this.worldBounds = worldBounds;
        this.explosionPool = explosionPool;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        reloadTimer += delta;
        switch (state) {
            case DESCENT:
                if (getTop() <= worldBounds.getTop()) {
                    v.set(v0);
                    state = State.FIGHT;
                    break;
                }
            case FIGHT:
                if (reloadTimer >= reloadInterval) {
                    reloadTimer = 0f;
                    shoot();
                }
                if (worldBounds.getBottom() > getBottom()) {
                    explosionShip();
                    destroy();
                }
                break;
        }
    }

    public void set(
            TextureRegion[] regions,
            Vector2 v0,
            TextureRegion bulletRegion,
            float bulletHeight,
            float bulletVY,
            int bulletDamage,
            float reloadInterval,
            float height,
            int hp
    ) {
        this.regions = regions;
        this.v0 = v0;
        this.bulletRegion = bulletRegion;
        this.bulletHeight = bulletHeight;
        this.bulletV.set(0, bulletVY);
        this.bulletDamage = bulletDamage;
        this.reloadInterval = reloadInterval;
        this.hp = hp;
        setHeightProportion(height);
        reloadTimer = reloadInterval;
        v.set(descentV);
        state = State.DESCENT;
    }

    public boolean isBulletCollision(Rect bullet){
        return !(
                bullet.getRight()< getLeft()
                || bullet.getRight() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop()< pos.y
        );
    }
}
