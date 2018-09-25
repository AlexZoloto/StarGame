package ru.geekbrains.stargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.awt.Image;

import ru.geekbrains.stargame.base.Sprite;
import ru.geekbrains.stargame.math.Rect;


public class Background extends Sprite{
    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }


}