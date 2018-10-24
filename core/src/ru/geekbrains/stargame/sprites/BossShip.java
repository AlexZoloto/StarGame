package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Ship;
import ru.geekbrains.stargame.math.Rect;
import ru.geekbrains.stargame.math.Rnd;
import ru.geekbrains.stargame.pool.BulletPool;
import ru.geekbrains.stargame.pool.ExplosionPool;

public class BossShip extends Ship {
    private enum State {DESCENT, FIGHT}

    private Vector2 v0 = new Vector2(Rnd.nextFloat(-0.3f, 0.3f), 0);
    private Vector2 descentV = new Vector2(0, -0.15f);

    private State state;

    public BossShip(TextureAtlas atlas, BulletPool bulletPool, ExplosionPool explosionPool, Sound shootSound, Rect worldBounds) {
        super(atlas.findRegion("boss_ship"), 1, 2, 2, bulletPool, explosionPool, shootSound);
        this.worldBounds = worldBounds;
        this.bulletRegion = atlas.findRegion("schuss_yellow");
        this.bulletDamage = 20;
        this.bulletHeight = 0.05f;
        this.bulletV.set(0, -0.025f);
        this.reloadInterval = 1f;
        this.hp = 50;
        setHeightProportion(getHeight());
        reloadTimer = reloadInterval;
        v.set(descentV);
        state = State.DESCENT;
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
                if (getRight() > worldBounds.getRight()) {
                    v0 = new Vector2(Rnd.nextFloat(-0.3f, 0f), 0);
                }
                if (getLeft() < worldBounds.getLeft()) {
                    v0 = new Vector2(Rnd.nextFloat(0, 0.3f), 0);
                }
                break;
        }
    }
}
