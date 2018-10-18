package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.ExplosionPool;

public class BossShip extends Ship {
    private enum State {DESCENT, FIGHT}

    private MainShip mainShip;
    private Vector2 v0 = new Vector2(0.3f, 0);
    private TextureRegion[] bossRegion;

    private ExplosionPool explosionPool;
    private  BulletPool bulletPool;

    private Sound shootSound;

    private State state;


    public BossShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound) {
        super(atlas.findRegion("boss_ship"),1,2,2, bulletPool, explosionPool, shootSound);
        this.bulletRegion = atlas.findRegion("schuss_yellow");
        this.bulletDamage = 15;
        this.bulletHeight = 0.03f;
        this.bulletV.set(0, -0.3f);
        this.reloadInterval = 0.4f;
        this.hp = 200;
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

    @Override
    public void resize(Rect worldBounds) {
        super.worldBounds = worldBounds;
        setBottom(worldBounds.getTop() + 0.05f);
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
