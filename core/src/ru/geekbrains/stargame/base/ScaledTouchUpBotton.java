package ru.geekbrains.stargame.base;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class ScaledTouchUpBotton extends Sprite {

    private float pressScale;
    private boolean pressed;
    private float pointer;
    private ActionListener actionListener;

    public ScaledTouchUpBotton(TextureRegion region, ActionListener actionListener, float pressScale) {
        super(region);
        this.pressScale = pressScale;
        this.actionListener = actionListener;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (pressed || !isMe(touch)){
            return false;
        }
        this.pointer = pointer;
        scale = pressScale;
        this.pressed = true;
        return true;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (this.pointer != pointer || !pressed){
            return false;
        }
        if (isMe(touch)){
            actionListener.actionListener(this);
            return true;
        }
        pressed = false;
        scale = 1f;
        return false;
    }
}
