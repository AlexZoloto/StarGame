package ru.geekbrains.stargame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.base.Sprite;

public class ButtonExit extends Sprite {
    private boolean pressed;
    private float pointer;
    public ButtonExit(TextureRegion region) {
        super(region);
        setHeightProportion(0.10f);
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (!pressed || !isMe(touch)){
            this.pointer = pointer;
            this.pressed = true;
            scale = 0.9f;
            return false;
        }
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || isMe(touch)){
            pressed = false;
            Gdx.app.exit();
            return false;
        }
        return false;
    }
}