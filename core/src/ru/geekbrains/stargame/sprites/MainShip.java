package ru.geekbrains.stargame.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;

public class MainShip extends Sprite {

    private Vector2 v1 = new Vector2(0.5f, 0);
//    private Vector2 v2 = new Vector2(0, 0.5f);
    private Vector2 speed = new Vector2();

    private boolean pressedLeft;
    private boolean pressedRight;

    private Rect worldBounds;
//    private boolean pressedTop;
//    private boolean pressedDown;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        setHeightProportion(0.15f);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(speed, delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + 0.1f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x){
            movedLeft();
        }else {
            movedRight();
        }
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        stop();
        return false;
    }

    public void keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                movedLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                movedRight();
                break;
//            case Input.Keys.UP:
//            case Input.Keys.W:
//                pressedTop = true;
//                movedTop();
//                break;
//            case Input.Keys.S:
//            case Input.Keys.DOWN:
//                pressedLeft = true;
//                movedBottom();
//                break;
        }
    }

    public void keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight){
                    movedRight();
                }
                else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft){
                    movedLeft();
                }
                else {
                    stop();
                }
                break;
//            case Input.Keys.UP:
//            case Input.Keys.W:
//                stop();
//                break;
//            case Input.Keys.S:
//            case Input.Keys.DOWN:
//                stop();
//                break;
        }
    }

    public void movedRight() {
        speed.set(v1);
    }

    public void movedLeft() {
        speed.set(v1).rotate(180);
    }
//    public void movedTop() {
//        speed.set(v2);
//    }    public void movedBottom() {
//        speed.set(v2).rotate(180);
//    }


    private void stop() {
        speed.set(0, 0);
    }
}
